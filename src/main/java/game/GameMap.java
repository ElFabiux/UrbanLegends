/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

/**
 *
 * @author Fabiux
 */
public class GameMap {

    private Tile[][] tileMatrix = new Tile[36][36];

    private static String[][] gameMap = {
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G", "W", "W", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "T1", "T1", "G", "G", "G", "G", "G", "T2", "G", "G", "G", "H6", "H4", "G", "G", "T3", "T3", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"R1", "G", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "F", "F", "F", "F", "F", "F", "F", "F", "O", "O", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "G", "G", "H5", "T", "H5", "G", "G", "T3", "T3", "T3", "T3", "T3", "T3", "T3", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "G", "G", "G", "T2", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"R1", "G", "L", "H3", "G", "G", "G", "G", "G", "G", "G", "G", "H5", "H5", "G", "G", "G", "G", "F", "F", "F", "W", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"R1", "T2", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "L", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"R1", "G", "L", "T3", "T3", "G", "G", "H4", "L", "H1", "G", "T3", "T3", "H2", "G", "H3", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "W", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "T3", "G", "G", "G", "T3", "L", "H5", "G", "T3", "T3", "G", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"R1", "G", "L", "H1", "G", "G", "G", "H6", "L", "G", "G", "T3", "G", "G", "T1", "G", "G", "G", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "W", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "T1", "G", "G", "G", "T3", "L", "T3", "G", "G", "T2", "G", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"R1", "G", "L", "G", "G", "G", "G", "G", "L", "H6", "G", "G", "G", "G", "G", "T1", "G", "G", "W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "H4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F", "R", "R", "F", "S4", "S2", "S2", "S2", "S2", "S3", "F"},
        {"R5", "R3", "L", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "R3", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R4", "R2", "L", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "R2", "F", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "T", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "F", "W", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "F"},
        {"R1", "G", "L", "G", "G", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "W", "F", "F", "F", "F", "F", "F", "F", "R", "R", "F", "F", "F", "F", "F", "F", "F", "W"},
        {"R1", "G", "G", "T6", "G", "G", "T3", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T5", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "T3", "T3", "T3"},
        {"R1", "T1", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "O5", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "T1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G", "O5", "G", "O5", "G", "G", "G", "G", "T3", "C", "T3", "G", "G", "G", "G", "G", "G", "G", "T3"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "T2", "G", "T1", "G", "G", "G", "T3", "G", "G", "G", "O5", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "T5", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "T3", "T6", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5"},
        {"R1", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "T3", "G", "G", "G", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "G", "T7", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G"},
        {"R1", "G", "T2", "G", "G", "T6", "G", "G", "G", "T1", "G", "G", "G", "G", "T5", "G", "G", "G", "O5", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "T3", "G", "G", "G"},
        {"R1", "G", "G", "T2", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "T3", "G", "O2"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "O2", "G", "O1", "G", "G", "G", "G"},
        {"R1", "G", "G", "G", "G", "T7", "G", "G", "T2", "G", "G", "G", "G", "G", "T3", "G", "T2", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "T3", "T3", "G", "G", "G", "T3", "G", "O1", "G", "G"},
        {"R1", "G", "T4", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T1", "G", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "G", "O3", "G", "G", "G", "O3", "G", "T3"},
        {"R1", "T3", "T3", "G", "G", "G", "T7", "G", "G", "G", "G", "G", "G", "T3", "G", "G", "G", "G", "O5", "G", "G", "G", "G", "G", "O5", "G", "G", "O3", "G", "G", "G", "T3", "G", "G", "G", "G"},
        {"R1", "T3", "T3", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G", "O5", "G", "G", "G", "O3", "G", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "O5", "G", "O1"},
        {"R1", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "T3", "T3", "T3", "G", "G", "G", "G", "G", "O5", "O5", "G", "G", "G", "G", "G", "O5", "G", "O2", "G", "O1", "G", "G", "G", "G", "G", "O3"},
        {"R1", "T5", "G", "G", "G", "G", "G", "G", "G", "G", "T4", "G", "G", "G", "G", "T2", "G", "G", "O5", "G", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "O3", "G", "G", "G", "O1", "G", "O1"},
        {"R1", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T6", "G", "O5", "G", "G", "G", "G", "G", "G", "G", "G", "G", "T3", "O5", "G", "O2", "G", "G", "G", "O5"},
        {"R1", "G", "G", "T7", "G", "T1", "G", "G", "G", "G", "G", "G", "G", "T5", "G", "G", "G", "G", "O5", "G", "T3", "T3", "G", "T3", "G", "G", "O1", "G", "G", "G", "G", "O3", "G", "O2", "O5", "O5"}
    };

    public static String[][] getMap() {
        return GameMap.gameMap;
    }

    /**
     * Loads the entire tile matrix recursively from the gameMap.
     */
    public void loadTileMatrix() {
        loadTileMatrixRecursively(gameMap, 0, 0);
    }

    public Tile[][] createTileMatrix(String[][] stringMatrix, 
            Tile[][] tileMatrix) {
        int rows = stringMatrix.length;
        int cols = stringMatrix[0].length;
        createTileMatrixRecursively(stringMatrix, tileMatrix, 0, 0);
        return tileMatrix;
    }

    private void createTileMatrixRecursively(String[][] stringMatrix,
            Tile[][] newTileMatrix, int row, int col) {
        // Caso base: Si hemos procesado todas las filas
        if (row >= stringMatrix.length) {
            return;
        }
        
        if(newTileMatrix[row][col] == null){
            newTileMatrix[row][col] = createTile(stringMatrix[row][col]);
        }else if
        (!stringMatrix[row][col].equals(newTileMatrix[row][col].getTileType())) {
            newTileMatrix[row][col] = createTile(stringMatrix[row][col]);
        }

        if (col < stringMatrix[row].length - 1) {
            // Si no hemos llegado al final de la fila, seguir en la misma fila
            createTileMatrixRecursively(stringMatrix, newTileMatrix, row, col + 1);
        } else {
            // Si hemos llegado al final de la fila, pasar a la siguiente fila
            createTileMatrixRecursively(stringMatrix, newTileMatrix, row + 1, 0);
        }
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

        loadTileMatrixRecursively(mapMatrix, row, col + 1);
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
            case "H1":
                return "2_1";
            case "H2":
                return "2_2";
            case "H3":
                return "2_3";
            case "H4":
                return "2_4";
            case "H5":
                return "2_5";
            case "H6":
                return "2_6";
            case "R1":
                return "3_1";
            case "R2":
                return "3_2";
            case "R3":
                return "3_3";
            case "R4":
                return "3_4";
            case "R5":
                return "3_5";
            case "T1":
                return "4_1";
            case "T2":
                return "4_2";
            case "T3":
                return "4_3";
            case "T4":
                return "4_4";
            case "T5":
                return "4_5";
            case "T6":
                return "4_6";
            case "T7":
                return "4_7";
            case "O1":
                return "5_1";
            case "O2":
                return "5_2";
            case "O3":
                return "5_3";
            case "C":
                return "5_4";
            case "O5":
                return "5_5";
            case "F":
                return "6_1";
            case "S2":
                return "6_2";
            case "S3":
                return "6_3";
            case "S4":
                return "6_4";
            case "O":
                return "6_5";
            case "R":
                return "6_6";
            case "W":
                return "6_7";
            case "P1":
                return "7_1";
            case "P2":
                return "7_2";
            case "P3":
                return "7_3";
            case "L1":
                return "8_1";
            case "L2":
                return "8_2";
            case "L3":
                return "8_3";
            case "L4":
                return "8_4";
            case "L5":
                return "8_5";
            case "L6":
                return "8_6";
            default:
                return "1_1";
        }
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
     * Fills a submatrix from the tileMatrix recursively.
     *
     * @param subMatrix The submatrix to fill.
     * @param startRow The starting row in the main tileMatrix.
     * @param startCol The starting column in the main tileMatrix.
     * @param row The current row in the submatrix.
     * @param col The current column in the submatrix.
     */
    private void fillSubMatrix(Tile[][] subMatrix, int startRow, int startCol, int row, int col) {
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
}
