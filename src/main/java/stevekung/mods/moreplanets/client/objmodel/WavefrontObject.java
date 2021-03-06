/*******************************************************************************
 * Copyright 2015 SteveKunG - More Planets Mod
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package stevekung.mods.moreplanets.client.objmodel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

/**
 *  Wavefront Object importer
 *  Based heavily off of the specifications found at http://en.wikipedia.org/wiki/Wavefront_.obj_file
 */
public class WavefrontObject implements IModelCustom
{
    private Pattern vertexPattern = Pattern.compile("(v( (\\-){0,1}\\d+(\\.\\d+)?){3,4} *\\n)|(v( (\\-){0,1}\\d+(\\.\\d+)?){3,4} *$)");
    private Pattern vertexNormalPattern = Pattern.compile("(vn( (\\-){0,1}\\d+(\\.\\d+)?){3,4} *\\n)|(vn( (\\-){0,1}\\d+(\\.\\d+)?){3,4} *$)");
    private Pattern textureCoordinatePattern = Pattern.compile("(vt( (\\-){0,1}\\d+\\.\\d+){2,3} *\\n)|(vt( (\\-){0,1}\\d+(\\.\\d+)?){2,3} *$)");
    private Pattern face_V_VT_VN_Pattern = Pattern.compile("(f( \\d+/\\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+/\\d+){3,4} *$)");
    private Pattern face_V_VT_Pattern = Pattern.compile("(f( \\d+/\\d+){3,4} *\\n)|(f( \\d+/\\d+){3,4} *$)");
    private Pattern face_V_VN_Pattern = Pattern.compile("(f( \\d+//\\d+){3,4} *\\n)|(f( \\d+//\\d+){3,4} *$)");
    private Pattern face_V_Pattern = Pattern.compile("(f( \\d+){3,4} *\\n)|(f( \\d+){3,4} *$)");
    private Pattern groupObjectPattern = Pattern.compile("([go]( [\\w\\d\\.]+) *\\n)|([go]( [\\w\\d\\.]+) *$)");
    private Matcher vertexMatcher, vertexNormalMatcher, textureCoordinateMatcher;
    private Matcher face_V_VT_VN_Matcher, face_V_VT_Matcher, face_V_VN_Matcher, face_V_Matcher;
    private Matcher groupObjectMatcher;
    public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    public ArrayList<Vertex> vertexNormals = new ArrayList<Vertex>();
    public ArrayList<TextureCoordinate> textureCoordinates = new ArrayList<TextureCoordinate>();
    public ArrayList<GroupObject> groupObjects = new ArrayList<GroupObject>();
    private GroupObject currentGroupObject;
    private String fileName;

    public WavefrontObject(ResourceLocation resource)
    {
        this.fileName = resource.toString();

        try
        {
            IResource res = Minecraft.getMinecraft().getResourceManager().getResource(resource);
            this.loadObjModel(res.getInputStream());
        }
        catch (IOException e)
        {
            throw new ModelFormatException("IO Exception reading model format", e);
        }
    }

    public WavefrontObject(String filename, InputStream inputStream)
    {
        this.fileName = filename;
        this.loadObjModel(inputStream);
    }

    private void loadObjModel(InputStream inputStream)
    {
        BufferedReader reader = null;
        String currentLine = null;
        int lineCount = 0;

        try
        {
            reader = new BufferedReader(new InputStreamReader(inputStream));

            while ((currentLine = reader.readLine()) != null)
            {
                lineCount++;
                currentLine = currentLine.replaceAll("\\s+", " ").trim();

                if (currentLine.startsWith("#") || currentLine.length() == 0)
                {
                    continue;
                }
                else if (currentLine.startsWith("v "))
                {
                    Vertex vertex = this.parseVertex(currentLine, lineCount);

                    if (vertex != null)
                    {
                        this.vertices.add(vertex);
                    }
                }
                else if (currentLine.startsWith("vn "))
                {
                    Vertex vertex = this.parseVertexNormal(currentLine, lineCount);

                    if (vertex != null)
                    {
                        this.vertexNormals.add(vertex);
                    }
                }
                else if (currentLine.startsWith("vt "))
                {
                    TextureCoordinate textureCoordinate = this.parseTextureCoordinate(currentLine, lineCount);

                    if (textureCoordinate != null)
                    {
                        this.textureCoordinates.add(textureCoordinate);
                    }
                }
                else if (currentLine.startsWith("f "))
                {
                    if (this.currentGroupObject == null)
                    {
                        this.currentGroupObject = new GroupObject("Default");
                    }

                    Face face = this.parseFace(currentLine, lineCount);

                    if (face != null)
                    {
                        this.currentGroupObject.faces.add(face);
                    }
                }
                else if (currentLine.startsWith("g ") | currentLine.startsWith("o "))
                {
                    GroupObject group = this.parseGroupObject(currentLine, lineCount);

                    if (group != null)
                    {
                        if (this.currentGroupObject != null)
                        {
                            this.groupObjects.add(this.currentGroupObject);
                        }
                    }
                    this.currentGroupObject = group;
                }
            }
            this.groupObjects.add(this.currentGroupObject);
        }
        catch (IOException e)
        {
            throw new ModelFormatException("IO Exception reading model format", e);
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (IOException e) {}

            try
            {
                inputStream.close();
            }
            catch (IOException e) {}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderAll()
    {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();

        if (this.currentGroupObject != null)
        {
            worldrenderer.startDrawing(this.currentGroupObject.glDrawingMode);
        }
        else
        {
            worldrenderer.startDrawing(GL11.GL_TRIANGLES);
        }
        this.tessellateAll(tessellator);
        tessellator.draw();
    }

    @SideOnly(Side.CLIENT)
    public void tessellateAll(Tessellator tessellator)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            groupObject.render(tessellator);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderOnly(String... groupNames)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            for (String groupName : groupNames)
            {
                if (groupName.equalsIgnoreCase(groupObject.name))
                {
                    groupObject.render();
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void tessellateOnly(Tessellator tessellator, String... groupNames)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            for (String groupName : groupNames)
            {
                if (groupName.equalsIgnoreCase(groupObject.name))
                {
                    groupObject.render(tessellator);
                }
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderPart(String partName)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            if (partName.equalsIgnoreCase(groupObject.name))
            {
                groupObject.render();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void tessellatePart(Tessellator tessellator, String partName)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            if (partName.equalsIgnoreCase(groupObject.name))
            {
                groupObject.render(tessellator);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderAllExcept(String... excludedGroupNames)
    {
        for (GroupObject groupObject : this.groupObjects)
        {
            boolean skipPart = false;

            for (String excludedGroupName : excludedGroupNames)
            {
                if (excludedGroupName.equalsIgnoreCase(groupObject.name))
                {
                    skipPart=true;
                }
            }
            if (!skipPart)
            {
                groupObject.render();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void tessellateAllExcept(Tessellator tessellator, String... excludedGroupNames)
    {
        boolean exclude;

        for (GroupObject groupObject : this.groupObjects)
        {
            exclude = false;

            for (String excludedGroupName : excludedGroupNames)
            {
                if (excludedGroupName.equalsIgnoreCase(groupObject.name))
                {
                    exclude = true;
                }
            }
            if (!exclude)
            {
                groupObject.render(tessellator);
            }
        }
    }

    private Vertex parseVertex(String line, int lineCount)
    {
        Vertex vertex = null;

        if (this.isValidVertexLine(line))
        {
            line = line.substring(line.indexOf(" ") + 1);
            String[] tokens = line.split(" ");

            try
            {
                if (tokens.length == 2)
                {
                    return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]));
                }
                else if (tokens.length == 3)
                {
                    return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
                }
            }
            catch (NumberFormatException e)
            {
                throw new ModelFormatException(String.format("Number formatting error at line %d", lineCount), e);
            }
        }
        else
        {
            throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
        }
        return vertex;
    }

    private Vertex parseVertexNormal(String line, int lineCount)
    {
        Vertex vertexNormal = null;

        if (this.isValidVertexNormalLine(line))
        {
            line = line.substring(line.indexOf(" ") + 1);
            String[] tokens = line.split(" ");

            try
            {
                if (tokens.length == 3)
                {
                    return new Vertex(Float.parseFloat(tokens[0]), Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
                }
            }
            catch (NumberFormatException e)
            {
                throw new ModelFormatException(String.format("Number formatting error at line %d",lineCount), e);
            }
        }
        else
        {
            throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
        }
        return vertexNormal;
    }

    private TextureCoordinate parseTextureCoordinate(String line, int lineCount)
    {
        TextureCoordinate textureCoordinate = null;

        if (this.isValidTextureCoordinateLine(line))
        {
            line = line.substring(line.indexOf(" ") + 1);
            String[] tokens = line.split(" ");

            try
            {
                if (tokens.length == 2)
                {
                    return new TextureCoordinate(Float.parseFloat(tokens[0]), 1 - Float.parseFloat(tokens[1]));
                }
                else if (tokens.length == 3)
                {
                    return new TextureCoordinate(Float.parseFloat(tokens[0]), 1 - Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
                }
            }
            catch (NumberFormatException e)
            {
                throw new ModelFormatException(String.format("Number formatting error at line %d",lineCount), e);
            }
        }
        else
        {
            throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
        }
        return textureCoordinate;
    }

    private Face parseFace(String line, int lineCount)
    {
        Face face = null;

        if (this.isValidFaceLine(line))
        {
            face = new Face();
            String trimmedLine = line.substring(line.indexOf(" ") + 1);
            String[] tokens = trimmedLine.split(" ");
            String[] subTokens = null;

            if (tokens.length == 3)
            {
                if (this.currentGroupObject.glDrawingMode == -1)
                {
                    this.currentGroupObject.glDrawingMode = GL11.GL_TRIANGLES;
                }
                else if (this.currentGroupObject.glDrawingMode != GL11.GL_TRIANGLES)
                {
                    throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 4, found " + tokens.length + ")");
                }
            }
            else if (tokens.length == 4)
            {
                if (this.currentGroupObject.glDrawingMode == -1)
                {
                    this.currentGroupObject.glDrawingMode = GL11.GL_QUADS;
                }
                else if (this.currentGroupObject.glDrawingMode != GL11.GL_QUADS)
                {
                    throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Invalid number of points for face (expected 3, found " + tokens.length + ")");
                }
            }

            // f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3 ...
            if (this.isValidFace_V_VT_VN_Line(line))
            {
                face.vertices = new Vertex[tokens.length];
                face.textureCoordinates = new TextureCoordinate[tokens.length];
                face.vertexNormals = new Vertex[tokens.length];

                for (int i = 0; i < tokens.length; ++i)
                {
                    subTokens = tokens[i].split("/");
                    face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
                    face.textureCoordinates[i] = this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
                    face.vertexNormals[i] = this.vertexNormals.get(Integer.parseInt(subTokens[2]) - 1);
                }
                face.faceNormal = face.calculateFaceNormal();
            }
            // f v1/vt1 v2/vt2 v3/vt3 ...
            else if (this.isValidFace_V_VT_Line(line))
            {
                face.vertices = new Vertex[tokens.length];
                face.textureCoordinates = new TextureCoordinate[tokens.length];

                for (int i = 0; i < tokens.length; ++i)
                {
                    subTokens = tokens[i].split("/");
                    face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
                    face.textureCoordinates[i] = this.textureCoordinates.get(Integer.parseInt(subTokens[1]) - 1);
                }
                face.faceNormal = face.calculateFaceNormal();
            }
            // f v1//vn1 v2//vn2 v3//vn3 ...
            else if (this.isValidFace_V_VN_Line(line))
            {
                face.vertices = new Vertex[tokens.length];
                face.vertexNormals = new Vertex[tokens.length];

                for (int i = 0; i < tokens.length; ++i)
                {
                    subTokens = tokens[i].split("//");
                    face.vertices[i] = this.vertices.get(Integer.parseInt(subTokens[0]) - 1);
                    face.vertexNormals[i] = this.vertexNormals.get(Integer.parseInt(subTokens[1]) - 1);
                }
                face.faceNormal = face.calculateFaceNormal();
            }
            // f v1 v2 v3 ...
            else if (this.isValidFace_V_Line(line))
            {
                face.vertices = new Vertex[tokens.length];

                for (int i = 0; i < tokens.length; ++i)
                {
                    face.vertices[i] = this.vertices.get(Integer.parseInt(tokens[i]) - 1);
                }
                face.faceNormal = face.calculateFaceNormal();
            }
            else
            {
                throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
            }
        }
        else
        {
            throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
        }
        return face;
    }

    private GroupObject parseGroupObject(String line, int lineCount)
    {
        GroupObject group = null;

        if (this.isValidGroupObjectLine(line))
        {
            String trimmedLine = line.substring(line.indexOf(" ") + 1);

            if (trimmedLine.length() > 0)
            {
                group = new GroupObject(trimmedLine);
            }
        }
        else
        {
            throw new ModelFormatException("Error parsing entry ('" + line + "'" + ", line " + lineCount + ") in file '" + this.fileName + "' - Incorrect format");
        }
        return group;
    }

    /***
     * Verifies that the given line from the model file is a valid vertex
     * @param line the line being validated
     * @return true if the line is a valid vertex, false otherwise
     */
    private boolean isValidVertexLine(String line)
    {
        if (this.vertexMatcher != null)
        {
            this.vertexMatcher.reset();
        }
        this.vertexMatcher = this.vertexPattern.matcher(line);
        return this.vertexMatcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid vertex normal
     * @param line the line being validated
     * @return true if the line is a valid vertex normal, false otherwise
     */
    private boolean isValidVertexNormalLine(String line)
    {
        if (this.vertexNormalMatcher != null)
        {
            this.vertexNormalMatcher.reset();
        }
        this.vertexNormalMatcher = this.vertexNormalPattern.matcher(line);
        return this.vertexNormalMatcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid texture coordinate
     * @param line the line being validated
     * @return true if the line is a valid texture coordinate, false otherwise
     */
    private boolean isValidTextureCoordinateLine(String line)
    {
        if (this.textureCoordinateMatcher != null)
        {
            this.textureCoordinateMatcher.reset();
        }
        this.textureCoordinateMatcher = this.textureCoordinatePattern.matcher(line);
        return this.textureCoordinateMatcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid face that is described by vertices, texture coordinates, and vertex normals
     * @param line the line being validated
     * @return true if the line is a valid face that matches the format "f v1/vt1/vn1 ..." (with a minimum of 3 points in the face, and a maximum of 4), false otherwise
     */
    private boolean isValidFace_V_VT_VN_Line(String line)
    {
        if (this.face_V_VT_VN_Matcher != null)
        {
            this.face_V_VT_VN_Matcher.reset();
        }
        this.face_V_VT_VN_Matcher = this.face_V_VT_VN_Pattern.matcher(line);
        return this.face_V_VT_VN_Matcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid face that is described by vertices and texture coordinates
     * @param line the line being validated
     * @return true if the line is a valid face that matches the format "f v1/vt1 ..." (with a minimum of 3 points in the face, and a maximum of 4), false otherwise
     */
    private boolean isValidFace_V_VT_Line(String line)
    {
        if (this.face_V_VT_Matcher != null)
        {
            this.face_V_VT_Matcher.reset();
        }
        this.face_V_VT_Matcher = this.face_V_VT_Pattern.matcher(line);
        return this.face_V_VT_Matcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid face that is described by vertices and vertex normals
     * @param line the line being validated
     * @return true if the line is a valid face that matches the format "f v1//vn1 ..." (with a minimum of 3 points in the face, and a maximum of 4), false otherwise
     */
    private boolean isValidFace_V_VN_Line(String line)
    {
        if (this.face_V_VN_Matcher != null)
        {
            this.face_V_VN_Matcher.reset();
        }
        this.face_V_VN_Matcher = this.face_V_VN_Pattern.matcher(line);
        return this.face_V_VN_Matcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid face that is described by only vertices
     * @param line the line being validated
     * @return true if the line is a valid face that matches the format "f v1 ..." (with a minimum of 3 points in the face, and a maximum of 4), false otherwise
     */
    private boolean isValidFace_V_Line(String line)
    {
        if (this.face_V_Matcher != null)
        {
            this.face_V_Matcher.reset();
        }
        this.face_V_Matcher = this.face_V_Pattern.matcher(line);
        return this.face_V_Matcher.matches();
    }

    /***
     * Verifies that the given line from the model file is a valid face of any of the possible face formats
     * @param line the line being validated
     * @return true if the line is a valid face that matches any of the valid face formats, false otherwise
     */
    private boolean isValidFaceLine(String line)
    {
        return this.isValidFace_V_VT_VN_Line(line) || this.isValidFace_V_VT_Line(line) || this.isValidFace_V_VN_Line(line) || this.isValidFace_V_Line(line);
    }

    /***
     * Verifies that the given line from the model file is a valid group (or object)
     * @param line the line being validated
     * @return true if the line is a valid group (or object), false otherwise
     */
    private boolean isValidGroupObjectLine(String line)
    {
        if (this.groupObjectMatcher != null)
        {
            this.groupObjectMatcher.reset();
        }
        this.groupObjectMatcher = this.groupObjectPattern.matcher(line);
        return this.groupObjectMatcher.matches();
    }

    public static class Vertex
    {
        public float x, y, z;

        public Vertex(float x, float y)
        {
            this(x, y, 0.0F);
        }

        public Vertex(float x, float y, float z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public class TextureCoordinate
    {
        public float u, v, w;

        public TextureCoordinate(float u, float v)
        {
            this(u, v, 0.0F);
        }

        public TextureCoordinate(float u, float v, float w)
        {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}