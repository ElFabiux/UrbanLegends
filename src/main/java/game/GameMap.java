/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author jorge
 * @author joxan
 * @author melani
 * @author fabian
 * @author ismael
 */
public class GameMap {

    private Tile[][] tileMatrix = new Tile[36][36];
    private Time time = new Time();
    private static String[][] gameMap = {
        {"R", "G", "G", "G", "G", "G", "G", "G", "G", "J", "G", "G", "G", "G", "G", "G", "G", "G", "W", "W", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "K", "K", "G", "G", "G", "G", "G", "J", "G", "G", "G", "Y", "N", "G", "G", "U", "U", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "$"},
        {"R", "G", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "H", "H", "H", "H", "H", "H", "H", "H", "@", "@", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "G", "G", "M", "T", "M", "G", "G", "U", "U", "U", "U", "U", "U", "U", "G", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "G", "G", "G", "J", "G", "G", "K", "G", "G", "G", "G", "G", "G", "G", "G", "H", "4", "2", "2", "2", "2", "3", "H", "#", "#", "H", "4", "2", "2", "2", "2", "3", "H"},
        {"R", "G", "L", "B", "G", "G", "G", "G", "G", "G", "G", "G", "M", "M", "G", "G", "G", "G", "H", "H", "H", "$", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "$"},
        {"R", "J", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "G", "G", "H", "4", "2", "2", "2", "2", "3", "H", "#", "#", "H", "4", "2", "2", "2", "2", "3", "H"},
        {"R", "G", "L", "U", "U", "G", "G", "N", "L", "C", "G", "U", "U", "V", "G", "B", "G", "G", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "$", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "U", "G", "G", "G", "U", "L", "M", "G", "U", "U", "G", "G", "G", "G", "G", "H", "4", "2", "2", "2", "2", "3", "H", "#", "#", "H", "4", "2", "2", "2", "2", "3", "H"},
        {"R", "G", "L", "C", "G", "G", "G", "Y", "L", "G", "G", "U", "G", "G", "K", "G", "G", "G", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "$", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "K", "G", "G", "G", "U", "L", "U", "G", "G", "J", "G", "G", "G", "G", "G", "H", "4", "2", "2", "2", "2", "3", "H", "#", "#", "H", "4", "2", "2", "2", "2", "3", "H"},
        {"R", "G", "L", "G", "G", "G", "G", "G", "L", "Y", "G", "G", "G", "G", "G", "K", "G", "G", "$", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "N", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "H", "4", "2", "2", "2", "2", "3", "H", "#", "#", "H", "4", "2", "2", "2", "2", "3", "H"},
        {"Q", "Z", "L", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"A", "S", "L", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "T", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "H", "$", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "H"},
        {"R", "G", "L", "G", "G", "G", "K", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "$", "H", "H", "H", "H", "H", "H", "H", "#", "#", "H", "H", "H", "H", "H", "H", "H", "$"},
        {"R", "G", "G", "P", "G", "G", "U", "G", "E", "G", "G", "J", "G", "G", "G", "G", "G", "O", "X", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G", "U", "U", "U"},
        {"R", "K", "G", "G", "G", "G", "I", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "X", "G", "U", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R", "K", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "I", "G", "G", "G", "X", "G", "X", "G", "G", "G", "G", "U", "W", "U", "G", "G", "G", "G", "G", "G", "G", "U"},
        {"R", "G", "G", "G", "G", "G", "G", "G", "J", "G", "K", "G", "G", "G", "U", "G", "G", "G", "X", "X", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G", "G", "G"},
        {"R", "G", "G", "G", "G", "O", "G", "G", "G", "G", "G", "K", "G", "G", "G", "U", "P", "G", "X", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X"},
        {"R", "G", "G", "G", "G", "G", "G", "U", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "X", "G", "G", "G", "U", "G", "G", "G", "G", "G", "G", "G"},
        {"R", "G", "G", "G", "G", "G", "G", "U", "U", "G", "G", "G", "G", "E", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G"},
        {"R", "G", "J", "G", "G", "P", "G", "G", "G", "K", "G", "G", "G", "G", "O", "G", "G", "G", "X", "U", "U", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "G", "U", "G", "G", "G"},
        {"R", "G", "G", "J", "G", "G", "G", "G", "G", "G", "G", "G", "P", "G", "G", "G", "G", "G", "G", "G", "G", "G", "X", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "U", "G", "F"},
        {"R", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "G", "D", "G", "G", "G", "G", "F"},
        {"R", "G", "G", "G", "G", "E", "G", "G", "J", "G", "G", "G", "G", "G", "U", "G", "J", "G", "X", "G", "G", "G", "G", "G", "G", "G", "U", "U", "G", "G", "G", "U", "G", "D", "G", "G"},
        {"R", "G", "I", "G", "G", "G", "G", "G", "G", "G", "G", "G", "K", "G", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "G", "G", "G", "U", "G", "1", "G", "G", "G", "1", "G", "U"},
        {"R", "U", "U", "G", "G", "G", "E", "G", "G", "G", "G", "G", "G", "U", "G", "G", "G", "G", "X", "G", "G", "G", "G", "G", "X", "G", "G", "1", "G", "G", "G", "U", "G", "G", "G", "G"},
        {"R", "U", "U", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O", "G", "G", "G", "X", "G", "G", "G", "1", "G", "G", "G", "G", "G", "G", "G", "G", "1", "G", "X", "G", "D"},
        {"R", "G", "G", "G", "G", "G", "G", "I", "G", "G", "U", "U", "U", "G", "G", "G", "G", "G", "X", "X", "G", "G", "G", "G", "G", "X", "G", "F", "G", "D", "G", "G", "G", "G", "G", "1"},
        {"R", "O", "G", "G", "G", "G", "G", "G", "G", "G", "I", "G", "G", "G", "G", "J", "G", "G", "X", "G", "G", "X", "G", "G", "G", "G", "G", "G", "G", "1", "G", "G", "G", "D", "G", "D"},
        {"R", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "P", "G", "X", "G", "G", "G", "G", "G", "G", "G", "G", "G", "U", "X", "G", "F", "G", "G", "G", "X"},
        {"R", "G", "G", "E", "G", "K", "G", "G", "G", "G", "G", "G", "G", "O", "G", "G", "G", "G", "X", "G", "U", "U", "G", "U", "G", "G", "D", "G", "G", "G", "G", "1", "G", "F", "X", "X"}
    };

    /**
     * Fills a submatrix from the tileMatrix recursively.
     *
     * @param subMatrix The submatrix to fill.
     * @param startRow The starting row in the main tileMatrix.
     * @param startCol The starting column in the main tileMatrix.
     * @param row The current row in the submatrix.
     * @param col The current column in the submatrix.
     */
    private void fillSubMatrix(Tile[][] subMatrix, int startRow, int startCol,
            int row, int col) {
        if (row >= subMatrix.length) {
            return;
        }

        if (col >= subMatrix[row].length) {
            fillSubMatrix(subMatrix, startRow, startCol, row + 1, 0);
            return;
        }

        subMatrix[row][col] = tileMatrix[startRow + row][startCol + col];
        fillSubMatrix(subMatrix, startRow, startCol, row, col + 1);
    }

    /**
     * Constructor that initializes the GameMap class with a Time object.
     *
     * @param time Time object used to manage the time in the game map.
     */
    public GameMap(Time time) {
        this.time = time;
    }

    /**
     * Constructor that initializes the GameMap class.
     */
    public GameMap() {

    }

    /**
     * Gets the original game map matrix in its string format.
     *
     * @return The string matrix representing the game map.
     */
    public static String[][] getMap() {
        return GameMap.gameMap;
    }

    /**
     * Gets the 18x18 matrix of tiles representing the cemetery map.
     *
     * @return The 18x18 cemetery tile matrix.
     */
    public Tile[][] getCemeteryMap() {
        Tile[][] cemeteryMap = new Tile[18][18];
        fillSubMatrix(cemeteryMap, 18, 18, 0, 0);
        return cemeteryMap;
    }

    /**
     * Gets the 18x18 matrix of tiles representing the church map.
     *
     * @return The 18x18 church tile matrix.
     */
    public Tile[][] getChurchMap() {
        Tile[][] churchMap = new Tile[18][18];
        fillSubMatrix(churchMap, 0, 18, 0, 0);
        return churchMap;
    }

    /**
     * Gets the 18x18 matrix of tiles representing the forest map.
     *
     * @return The 18x18 forest tile matrix.
     */
    public Tile[][] getForestMap() {
        Tile[][] forestMap = new Tile[18][18];
        fillSubMatrix(forestMap, 18, 0, 0, 0);
        return forestMap;
    }

    /**
     * Gets the 18x18 matrix of tiles representing the village map.
     *
     * @return The 18x18 village tile matrix.
     */
    public Tile[][] getVillageMap() {
        Tile[][] villageMap = new Tile[18][18];
        fillSubMatrix(villageMap, 0, 0, 0, 0);
        return villageMap;
    }

    /**
     * Maps a tile type to its corresponding image path.
     *
     * @param tileType The string representing the tile type.
     * @return The image path corresponding to the tile type.
     */
    private String getTileImagePath(String tileType) {
        switch (tileType) {
            case "1_1":
                return "/Grass/1_1.jpg";
            case "1_2":
                return "/Grass/1_2.png";
            case "1_3":
                return "/Grass/1_3.png";
            case "2_1":
                return "/House/2_1.jpg";
            case "2_2":
                return "/House/2_2.jpg";
            case "2_3":
                return "/House/2_3.jpg";
            case "2_4":
                return "/House/2_4.jpg";
            case "2_5":
                return "/House/2_5.jpg";
            case "2_6":
                return "/House/2_6.jpg";
            case "3_1":
                return "/Water/3_1.jpg";
            case "3_2":
                return "/Water/3_2.jpg";
            case "3_3":
                return "/Water/3_3.jpg";
            case "3_4":
                return "/Water/3_4.jpg";
            case "3_5":
                return "/Water/3_5.jpg";
            case "4_1":
                return "/Forest/4_1.png";
            case "4_2":
                return "/Forest/4_2.png";
            case "4_3":
                return "/Forest/4_3.png";
            case "4_4":
                return "/Forest/4_4.png";
            case "4_5":
                return "/Forest/4_5.png";
            case "4_6":
                return "/Forest/4_6.png";
            case "4_7":
                return "/Forest/4_7.png";
            case "5_1":
                return "/Cemetery/5_1.png";
            case "5_2":
                return "/Cemetery/5_2.png";
            case "5_3":
                return "/Cemetery/5_3.png";
            case "5_4":
                return "/Cemetery/5_4.png";
            case "5_5":
                return "/Cemetery/5_5.png";
            case "6_1":
                return "/Church/6_1.png";
            case "6_2":
                return "/Church/6_2.png";
            case "6_3":
                return "/Church/6_3.png";
            case "6_4":
                return "/Church/6_4.png";
            case "6_5":
                return "/Church/6_5.png";
            case "6_6":
                return "/Church/6_6.png";
            case "6_7":
                return "/Church/6_7.png";
            case "7_1":
                return "/Characters/7_1.png";
            case "7_2":
                return "/Characters/7_2.png";
            case "7_3":
                return "/Characters/7_3.png";
            case "8_1":
                return "/Legends/8_1.png";
            case "8_2":
                return "/Legends/8_2.png";
            case "8_3":
                return "/Legends/8_3.png";
            case "8_4":
                return "/Legends/8_4.png";
            case "8_5":
                return "/Legends/8_5.png";
            case "8_6":
                return "/Legends/8_6.png";
            default:
                return "/Grass/1_1.jpg";
        }
    }

    /**
     * Maps a tile character to the corresponding tile type.
     *
     * @param tileChar The character representing the tile type.
     * @return A string representing the tile type, mapped to its corresponding
     * image.
     */
    private String getTileType(String tileChar) {
        switch (tileChar) {
            case "G":
                return "1_1";
            case "L":
                return "1_2";
            case "T":
                return "1_3";
            case "C":
                return "2_1";
            case "V":
                return "2_2";
            case "B":
                return "2_3";
            case "N":
                return "2_4";
            case "M":
                return "2_5";
            case "Y":
                return "2_6";
            case "R":
                return "3_1";
            case "S":
                return "3_2";
            case "Z":
                return "3_3";
            case "A":
                return "3_4";
            case "Q":
                return "3_5";
            case "K":
                return "4_1";
            case "J":
                return "4_2";
            case "U":
                return "4_3";
            case "I":
                return "4_4";
            case "O":
                return "4_5";
            case "P":
                return "4_6";
            case "E":
                return "4_7";
            case "D":
                return "5_1";
            case "F":
                return "5_2";
            case "1":
                return "5_3";
            case "W":
                return "5_4";
            case "X":
                return "5_5";
            case "H":
                return "6_1";
            case "2":
                return "6_2";
            case "3":
                return "6_3";
            case "4":
                return "6_4";
            case "@":
                return "6_5";
            case "#":
                return "6_6";
            case "$":
                return "6_7";
            case "%":
                return "7_1";
            case "&":
                return "7_2";
            case "*":
                return "7_3";
            case "5":
                return "8_1";
            case "6":
                return "8_2";
            case "7":
                return "8_3";
            case "8":
                return "8_4";
            case "9":
                return "8_5";
            case "0":
                return "8_6";
            default:
                return "1_1";
        }
    }

    /**
     * Creates a Tile object based on the character representing the tile type.
     *
     * @param tileChar The character representing the tile type.
     * @return The Tile object corresponding to the tile character.
     */
    public Tile createTile(String tileChar) {
        String tileType = getTileType(tileChar);
        String imagePath = getTileImagePath(tileType);
        return new Tile(tileType, imagePath);
    }

    /**
     * Retrieves the current Time object.
     *
     * @return The Time object associated with the game map.
     */
    public Time getTime() {
        return this.time;
    }

    /**
     * Changes the time of day in the Time object, affecting the state of the
     * observer tiles.
     *
     * @param isDaytime Boolean value indicating if it’s daytime (true) or
     * nighttime (false).
     */
    public void changeTime(boolean isDaytime) {
        time.setTimeOfDay(isDaytime);  // Set time and notify tiles
    }

    /**
     * Creates a Tile matrix from a given string matrix.
     *
     * This method initializes a matrix of Tile objects based on the provided
     * string matrix, representing each tile type in the map.
     *
     * @param stringMatrix The source matrix containing tile type strings.
     * @param tileMatrix The target matrix to store the created Tile objects.
     * @return The populated tile matrix based on the input string matrix.
     */
    public Tile[][] createTileMatrix(String[][] stringMatrix,
            Tile[][] tileMatrix) {
        int rows = stringMatrix.length;
        int cols = stringMatrix[0].length;
        createTileMatrixRecursively(stringMatrix, tileMatrix, 0, 0);
        return tileMatrix;
    }

    /**
     * Recursively populates the tile matrix from the string matrix.
     *
     * This method uses recursion to iterate over the rows and columns of the
     * string matrix, creating a Tile object for each entry in the matrix.
     *
     * @param stringMatrix The source matrix containing tile type strings.
     * @param newTileMatrix The target matrix where Tile objects will be stored.
     * @param row The current row being processed.
     * @param col The current column being processed.
     */
    private void createTileMatrixRecursively(String[][] stringMatrix,
            Tile[][] newTileMatrix, int row, int col) {
        if (row >= stringMatrix.length) {
            return;
        }

        if (newTileMatrix[row][col] == null) {
            newTileMatrix[row][col] = createTile(stringMatrix[row][col]);
        } else if (!stringMatrix[row][col].equals(newTileMatrix[row][col]
                .getTileType())) {
            newTileMatrix[row][col] = createTile(stringMatrix[row][col]);
        }

        if (col < stringMatrix[row].length - 1) {
            createTileMatrixRecursively(stringMatrix, newTileMatrix, row,
                    col + 1);
        } else {
            createTileMatrixRecursively(stringMatrix, newTileMatrix, row + 1,
                    0);
        }
    }

    /**
     * Loads the entire tile matrix recursively from the gameMap.
     */
    public void loadTileMatrix() {
        loadTileMatrixRecursively(gameMap, 0, 0);
    }

    /**
     * Recursively loads the tile matrix from the gameMap string matrix.
     *
     * @param mapMatrix The source map matrix.
     * @param row The current row being processed.
     * @param col The current column being processed.
     */
    private void loadTileMatrixRecursively(String[][] mapMatrix, int row, int col) {
        if (row >= mapMatrix.length) {
            return;
        }

        if (col >= mapMatrix[row].length) {
            loadTileMatrixRecursively(mapMatrix, row + 1, 0);
            return;
        }

        String tileChar = mapMatrix[row][col];
        Tile tile = createTile(tileChar);
        tileMatrix[row][col] = tile;

        time.addObserver(tile);

        loadTileMatrixRecursively(mapMatrix, row, col + 1);
    }
}
