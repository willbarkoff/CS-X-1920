package com.williambarkoff.csx.matrices;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    private int rows;
    private int cols;

    /**
     * Creates a new matrix
     *
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
    }

    /**
     * Creates a new matrix populated with random double values
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param min  minimum for random
     * @param max  maximum for random
     */
    public Matrix(int rows, int cols, int min, int max) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = Math.random() * (max - min) + min;
            }
        }
    }

    /**
     * Creates a new matrix from a 2D number array
     *
     * @param array array to create matrix from
     */
    public Matrix(double[][] array) {
        this.matrix = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }

    /**
     * Gets the value at a specific spot in the matrix
     *
     * @param rowNumber the row of the targeted spot
     * @param colNumber the column of the targeted spot
     * @param value     the value at the given spot
     */
    public void setEntry(int rowNumber, int colNumber, double value) {
        matrix[rowNumber][colNumber] = value;
    }

    /**
     * Gets the value at a specific spot in the matrix
     *
     * @param rowNumber the row of the targeted spot
     * @param colNumber the column of the targeted spot
     *
     * @return the value at the given spot
     */
    public double getEntry(int rowNumber, int colNumber) {
        return matrix[rowNumber][colNumber];
    }

    /**
     * Returns a matrix that is the sum of a given matrix and this matrix.
     *
     * @param matrix Matrix to add
     *
     * @return Sum of the matrices.
     *
     * @throws InvalidDimensionsException If the matrices are not the same size.
     */
    public Matrix plus(Matrix matrix) throws InvalidDimensionsException {
        compareDimensions(matrix);
        Matrix newMatrix = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.matrix[i][j] = matrix.matrix[i][j] + this.matrix[i][j];
            }
        }

        return newMatrix;
    }

    /**
     * Returns a matrix that is the difference of a given matrix and this matrix.
     *
     * @param matrix Matrix to subtract
     *
     * @return Difference of the matrices.
     *
     * @throws InvalidDimensionsException If the matrices are not the same size.
     */
    public Matrix minus(Matrix matrix) throws InvalidDimensionsException {
        compareDimensions(matrix);
        Matrix newMatrix = new Matrix(this.rows, this.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                newMatrix.matrix[i][j] = matrix.matrix[i][j] - this.matrix[i][j];
            }
        }

        return newMatrix;
    }

    /**
     * Multiplies this Matrix by a given Matrix and returns the product Matrix.
     *
     * <i>Note that matrix multiplication is not commutative.</i>
     *
     * @param matrix Matrix to be multiplied by this Matrix
     *
     * @return product of the two matrices
     *
     * @throws InvalidDimensionsException if the number of rows of this matrix is not equal to the number of columns of
     *                                    the specified matrix.
     */
    public Matrix times(Matrix matrix) throws InvalidDimensionsException {
        if (this.cols != matrix.rows) {
            throw new InvalidDimensionsException(this.cols, matrix.rows, "rows");
        }

        Matrix result = new Matrix(this.rows, matrix.cols);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                double sum = 0;

                for (int k = 0; k < this.cols; k++) {
                    sum += this.matrix[j][k] * matrix.matrix[k][j];
                }

                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    /**
     * Returns a Matrix that has multiplied a scalar by a row of this Matrix.
     *
     * @param scalar number by which each entry in the specified row will be multiplied
     * @param row    row being multiplied by a scalar
     *
     * @return Matrix with the modified row
     */
    public Matrix scalarTimesRow(double scalar, int row) {
        Matrix result = copy();

        for (int i = 0; i < result.matrix[row].length; i++) {
            result.matrix[row][i] *= scalar;
        }

        return result;
    }

    /**
     * Returns a Matrix that has switched two rows in this Matrix.
     *
     * @param firstRow  first row being switched
     * @param secondRow second row being switched
     *
     * @return Matrix with the switched rows
     */
    public Matrix switchRows(int firstRow, int secondRow) {
        Matrix result = copy();

        double[] secondRowHolder = result.matrix[secondRow];
        result.matrix[secondRow] = result.matrix[firstRow];
        result.matrix[firstRow] = secondRowHolder;

        return result;
    }

    /**
     * Returns a matrix that has added a scalar multiple of the first row to the second row of this Matrix.
     *
     * @param scalar    number by which each entry in the first row will be multiplied
     * @param firstRow  row being multiplied by a scalar and added to the second row
     * @param secondRow row to which the first row is added
     *
     * @return Matrix with the modified secondRow
     */
    public Matrix linearComboRows(double scalar, int firstRow, int secondRow) {
        Matrix result = copy();

        for (int i = 0; i < result.matrix[secondRow].length; i++) {
            result.matrix[secondRow][i] += scalar * result.matrix[firstRow][i];
        }

        return result;
    }

    /**
     * Row reduces the matrix
     *
     * @return row-reduced matrix
     */
    public Matrix rowReduce() throws PivotNotFoundException {
        Matrix result = copy();
        boolean[][] pivots = new boolean[rows][cols];
        for (int i = 0; i < pivots.length; i++) {
            Arrays.fill(pivots[i], false);
        }

        for (int i = 0; i < Math.min(rows, cols); i++) {
            try {
                if (result.findPivotRow(0, i) != i) {
                    result = result.switchRows(result.findPivotRow(0, i), i);
                    result = result.scalarTimesRow(1 / result.matrix[result.findPivotRow(0, i)][i], result.findPivotRow(0, i));
                    result = result.zeroDown(result.findPivotRow(0, i), i);
                }
            } catch (PivotNotFoundException e) {
                System.out.println("caught");
                e.printStackTrace();
            }
        }

        return result;
    }

    protected Matrix zeroDown(int row, int col) {
        Matrix result = copy();
        for (int i = (row + 1); i < rows; i++) {
            linearComboRows(-result.matrix[i][col] / result.matrix[row][col], row, i);
        }

        return result;
    }

    /**
     * Takes an excerpt of a matrix
     *
     * @param fromRow row to start excerpt
     * @param fromCol column to start excerpt
     *
     * @return excerpt
     */
    protected Matrix excerpt(int fromRow, int fromCol) {
        Matrix result = new Matrix(rows - fromRow, cols - fromCol);
        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                result.matrix[i][j] = matrix[i + fromRow][j + fromCol];
            }
        }

        return result;
    }

    protected Matrix insertExcerpt(Matrix excerpt, int startRow, int startCol) {
        Matrix result = copy();

        for (int i = 0; i < excerpt.rows; i++) {
            if (excerpt.cols >= 0) {
                System.arraycopy(excerpt.matrix[i], 0, result.matrix[i + startRow], startCol, excerpt.cols);
            }
        }

        return result;
    }

    /**
     * Pivots the matrix
     *
     * @return a pivoted version of the matrix
     */
    public Matrix pivot() {
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] != 0) {
                return switchRows(0, i).scalarTimesRow(1 / getEntry(i, 0), 0);
            }
        }

        //TODO: Returning a copy may be wrong here, this is an edge case
        return copy();
    }

    public int findPivotRow(int row, int col) throws PivotNotFoundException {
        if (matrix[row][col] != 0) {
            return col;
        } else {
            for (int i = rows; i < this.rows; i++) {
                if (matrix[i][col] != 0) {
                    return i;
                }
            }
        }
        throw new PivotNotFoundException();
    }

    /**
     * Returns a copy of the matrix
     *
     * @return copy of the matrix
     */
    protected Matrix copy() {
        return new Matrix(this.matrix);
    }

    /**
     * Compares the size of the matrices
     *
     * @param matrix matrix to compare to
     *
     * @throws InvalidDimensionsException If they aren't the same size
     */
    private void compareDimensions(Matrix matrix) throws InvalidDimensionsException {
        if (this.cols != matrix.cols || this.rows != matrix.rows) {
            throw new InvalidDimensionsException(this.rows, this.cols, matrix.rows, matrix.cols);
        }
    }

    /**
     * Returns a string that can be parsed by <a href="https://wolframalpha.com">Wolfram|Alpha</a>.
     *
     * @return String for use with Wolfram|Alpha
     */
    public String getWolframAlphaString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < matrix.length; i++) {
            sb.append("{");
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                if (j != matrix[i].length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
            if (i != matrix.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");

        return sb.toString();
    }

    /**
     * Returns a {@code String} object representing the matrix's value
     *
     * @return string value of the matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            if (i == 0) {
                sb.append("⌈\t");
            } else if (i == rows - 1) {
                sb.append("\n⌊\t");
            } else {
                sb.append("\n|\t");
            }
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]).append("\t");
            }
            if (i == 0) {
                sb.append("⌉");
            } else if (i == rows - 1) {
                sb.append("⌋");
            } else {
                sb.append("|");
            }
        }
        return sb.toString();
    }
}
