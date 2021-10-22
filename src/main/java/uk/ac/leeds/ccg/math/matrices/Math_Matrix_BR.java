/*
 * Copyright 2020 Centre for Computational Geography, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.math.matrices;

import java.util.Arrays;
import uk.ac.leeds.ccg.math.number.Math_BigRational;

/**
 * For processing matrices holding Math_BigRational numbers.
 *
 * @author Andy Turner
 * @version 1.0
 */
public class Math_Matrix_BR {

    /**
     * The rows.
     */
    protected final Math_BigRational[][] rows;

    /**
     * The columns.
     */
    protected final Math_BigRational[][] cols;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Transpose">Transpose</a> of the
     * matrix.
     */
    protected Math_Matrix_BR mt;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Row_echelon_form">Row Echelon
     * Form</a> of the matrix.
     */
    protected Math_Matrix_BR ref;

    /**
     * For storing the
     * <a href="https://en.wikipedia.org/wiki/Row_echelon_form#Reduced_row_echelon_form">Reduced
     * Row Echelon Form</a> of the matrix.
     */
    protected Math_Matrix_BR rref;

    /**
     * Create a new instance. External changes to m will not be reflected in
     * this.
     *
     * @param m A rectangular matrix used to construct this.
     */
    public Math_Matrix_BR(Math_BigRational[][] m) {
        int nr = m.length;
        int nc = m[0].length;
        this.rows = new Math_BigRational[nr][nc];
        this.cols = new Math_BigRational[nc][nr];
        for (int r = 0; r < nr; r++) {
            for (int c = 0; c < nc; c++) {
                rows[r][c] = m[r][c];
                cols[c][r] = m[r][c];
            }
        }
    }

    /**
     * Create a new instance.
     *
     * @param rows What {@link #rows} is set to.
     * @param cols What {@link #cols} is set to.
     */
    protected Math_Matrix_BR(Math_BigRational[][] rows, Math_BigRational[][] cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public String toString() {
        String r = this.getClass().getSimpleName() + "(";
            r += "\n";
        for (Math_BigRational[] row : rows) {
            for (int col = 0; col < cols.length; col++) {
                r += "" + row[col] + " ";
            }
            r += "\n";
        }
        r += ")";
        return r;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Math_Matrix_BR) {
            return equals((Math_Matrix_BR) o);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Arrays.deepHashCode(this.rows);
        hash = 11 * hash + Arrays.deepHashCode(this.cols);
        return hash;
    }

    /**
     * @param m The Math_Matrix_BR to test for equality with this.
     * @return {@code true} iff this is equal to {@code m}
     */
    public boolean equals(Math_Matrix_BR m) {
        if (rows.length == m.rows.length && cols.length == m.cols.length) {
            for (int r = 0; r < rows.length; r++) {
                for (int c = 0; c < cols.length; c++) {
                    if (this.rows[r][c].compareTo(m.rows[r][c]) != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Matrix_multiplication">Matrix
     * multiplication</a>
     *
     * @param m The matrix to multiply {@code this} by.
     * @return Result of multiplying {@code this} by {@code m}, or {@code null}
     * if {@code cols.length != m.rows.length}.
     */
    public Math_Matrix_BR multiply(Math_Matrix_BR m) {
        Math_Matrix_BR r = null;
        if (cols.length == m.rows.length) {
            Math_BigRational[][] rrows = new Math_BigRational[rows.length][m.cols.length];
            Math_BigRational[][] rcols = new Math_BigRational[m.cols.length][rows.length];
            for (int row = 0; row < rows.length; row++) {
                for (int col = 0; col < m.cols.length; col++) {
                    Math_BigRational v = Math_BigRational.ZERO;
                    for (int i = 0; i < m.rows.length; i++) {
                        v = v.add(rows[row][i].multiply(m.rows[i][col]));
                    }
                    rrows[row][col] = v;
                    rcols[col][row] = v;
                }
            }
            r = new Math_Matrix_BR(rrows, rcols);
        }
        return r;
    }

    /**
     * https://en.wikipedia.org/wiki/Scalar_multiplication Left scalar
     * multiplication
     *
     * @param s The scalar to multiply by.
     * @return Result of multiplying {@code this} by {@code s}.
     */
    public Math_Matrix_BR multiply(Math_BigRational s) {
        Math_Matrix_BR r;
        Math_BigRational[][] rrows = new Math_BigRational[rows.length][cols.length];
        Math_BigRational[][] rcols = new Math_BigRational[cols.length][rows.length];
        for (int row = 0; row < rows.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                Math_BigRational v = rows[row][col].multiply(s);
                rrows[row][col] = v;
                rcols[col][row] = v;
            }
        }
        r = new Math_Matrix_BR(rrows, rcols);
        return r;
    }

    /**
     * https://en.wikipedia.org/wiki/Matrix_addition
     *
     * @param m The matrix to add to {@code this}.
     * @return result of adding {@code this} to {@code b} or {@code null} if the
     * {@code this} and {@code b} have different dimensions.
     */
    public Math_Matrix_BR add(Math_Matrix_BR m) {
        Math_Matrix_BR r = null;
        if (cols.length == m.cols.length) {
            if (rows.length == m.rows.length) {
                Math_BigRational[][] rrows = new Math_BigRational[rows.length][cols.length];
                Math_BigRational[][] rcols = new Math_BigRational[cols.length][rows.length];
                for (int row = 0; row < rows.length; row++) {
                    for (int col = 0; col < cols.length; col++) {
                        Math_BigRational v = rows[row][col].add(m.rows[row][col]);
                        rrows[row][col] = v;
                        rcols[col][row] = v;
                    }
                }
                r = new Math_Matrix_BR(rrows, rcols);
            }
        }
        return r;
    }

    /**
     * https://en.wikipedia.org/wiki/Determinant Calculates and returns the
     * determinant of {@code this}.
     *
     * @return The calculated determinant of {@code this}.
     */
    public Math_BigRational getDeterminant() {
        if (rows.length == cols.length) {
            return getDeterminant(rows);
        } else {
            throw new RuntimeException("Cannot calculate determinant of matrix "
                    + "as it is not square.");
        }
    }

    private Math_BigRational getDeterminant(Math_BigRational[][] m) {
        switch (m.length) {
            case 1:
                return m[0][0];
            case 2:
                // ab
                // cd
                // ad - bc
                return (m[0][0].multiply(m[1][1]))
                        .subtract(m[0][1].multiply(m[1][0]));
            case 3:
                // Leibniz formula
                // abc
                // def
                // ghi
                // aei + bfg + cdh - ceg - bdi - afh
                return (m[0][0].multiply(m[1][1]).multiply(m[2][2]))
                        .add(m[0][1].multiply(m[1][2]).multiply(m[2][0]))
                        .add(m[0][2].multiply(m[1][0]).multiply(m[2][1]))
                        .subtract(m[0][2].multiply(m[1][1]).multiply(m[2][0]))
                        .subtract(m[0][1].multiply(m[1][0]).multiply(m[2][2]))
                        .subtract(m[0][0].multiply(m[1][2]).multiply(m[2][1]));
            default:
                // Use Laplace formula recursively
                Math_BigRational r = Math_BigRational.ZERO;
                for (int i = 0; i < m.length; i++) {
                    Math_BigRational sr = m[0][i].multiply(getDeterminant(
                            getSubMatrix(m, i)));
                    if (i % 2 == 0) {
                        r = r.add(sr);
                    } else {
                        r = r.subtract(sr);
                    }
                }
                return r;
        }
    }

    private Math_BigRational[][] getSubMatrix(Math_BigRational[][] m, int col) {
        int len = m.length;
        int len2 = len - 1;
        int col2 = col + 1;
        int r = 0;
        Math_BigRational[][] res = new Math_BigRational[len2][len2];
        for (int i = 1; i < len; i++) {
            int c = 0;
            for (int j = 0; j < col; j++) {
                res[r][c] = m[i][j];
                c++;
            }
            for (int j = col2; j < len; j++) {
                res[r][c] = m[i][j];
                c++;
            }
            r++;
        }
        return res;
    }

    /**
     * https://en.wikipedia.org/wiki/Identity_matrix
     *
     * @param size The dimension of the identity matrix.
     * @return A matrix with values equal to 1 on the diagonal and 0 elsewhere;
     */
    public static Math_Matrix_BR getIdentityMatrix(int size) {
        Math_Matrix_BR r;
        Math_BigRational[][] rrows = getMatrix(size, size, Math_BigRational.ZERO);
        Math_BigRational[][] rcols = getMatrix(size, size, Math_BigRational.ZERO);
        for (int i = 0; i < size; i++) {
            rrows[i][i] = Math_BigRational.ONE;
            rcols[i][i] = Math_BigRational.ONE;
        }
        r = new Math_Matrix_BR(rrows, rcols);
        return r;
    }

    /**
     * @param rows The number of rows.
     * @param cols The number of cols
     * @param v The value.
     * @return A matrix with the given number of rows and cols with all values
     * equal to v.
     */
    public static Math_BigRational[][] getMatrix(int rows, int cols, Math_BigRational v) {
        Math_BigRational[][] m = new Math_BigRational[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                m[r][c] = v;
            }
        }
        return m;
    }

    /**
     * @return A clone of {@link #rows}.
     */
    public Math_BigRational[][] getRows() {
        Math_BigRational[][] m = new Math_BigRational[rows.length][cols.length];
        for (int r = 0; r < rows.length; r++) {
            System.arraycopy(rows[r], 0, m[r], 0, cols.length);
        }
        return m;
    }

    /**
     * @return A clone of {@link #cols}.
     */
    public Math_BigRational[][] getCols() {
        Math_BigRational[][] m = new Math_BigRational[cols.length][rows.length];
        for (int c = 0; c < cols.length; c++) {
            System.arraycopy(cols[c], 0, m[c], 0, rows.length);
        }
        return m;
    }

    /**
     * This will also store the transpose in {@link #mt} and likewise store
     * {@code this} as the transpose in that. For details of what the transpose
     * of a matrix is see
     * <a href="https://en.wikipedia.org/wiki/Transpose">https://en.wikipedia.org/wiki/Transpose</a>.
     *
     * @return {@code this} transposed
     */
    public Math_Matrix_BR getTranspose() {
        if (mt == null) {
            Math_BigRational[][] rrows = new Math_BigRational[cols.length][rows.length];
            Math_BigRational[][] rcols = new Math_BigRational[rows.length][cols.length];
            for (int row = 0; row < rows.length; row++) {
                for (int col = 0; col < cols.length; col++) {
                    rrows[col][row] = rows[row][col];
                    rcols[row][col] = rows[row][col];
                }
            }
            mt = new Math_Matrix_BR(rrows, rcols);
            mt.mt = this;
        }
        return mt;
    }

    /**
     * Is symmetric.
     *
     * @return {@code true} iff {@code this} is symmetric.
     */
    public boolean isSymmetric() {
        return getTranspose().equals(this);
    }

    /**
     * @param row The row index.
     * @return {@code true} iff the row has all zero values.
     */
    public boolean isZeroRow(int row) {
        return isZeroRow(rows, row);
    }

    /**
     * @param m The matrix in row major order.
     * @param row The row index.
     * @return {@code true} iff the row has all zero values.
     */
    public static boolean isZeroRow(Math_BigRational[][] m, int row) {
        for (int col = 0; col < m[0].length; col++) {
            if (m[row][col].compareTo(Math_BigRational.ZERO) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return {@code true} iff all values are zero.
     */
    public boolean isZero() {
        for (Math_BigRational[] row : rows) {
            for (int c = 0; c <= cols.length; c++) {
                if (row[c].compareTo(Math_BigRational.ZERO) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param col The column index.
     * @return {@code true} iff the column has all zero values.
     */
    public boolean isZeroCol(int col) {
        return isZeroCol(rows, col);
    }

    /**
     * @param m The matrix in row major order.
     * @param col The column index.
     * @return {@code true} iff the column has all zero values.
     */
    public boolean isZeroCol(Math_BigRational[][] m, int col) {
        for (Math_BigRational[] row : m) {
            if (row[col].compareTo(Math_BigRational.ZERO) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * The rank of the matrix is the number of linearly independent rows.
     *
     * @return The rank of the matrix.
     */
    public int getRank() {
        int r = 0;
        Math_Matrix_BR m = getReducedRowEchelonForm();
        for (int i = 0; i < m.rows.length; i++) {
            if (!m.isZeroRow(i)) {
                r++;
            }
        }
        return r;
    }

    /**
     * Swap rows.
     *
     * @param m The matrix rows by columns.
     * @param i Row index of a row to swap.
     * @param r Row index of a row to swap.
     */
    private void swapRows(Math_BigRational[][] m, int i, int r) {
        Math_BigRational[] mr0 = m[r];
        m[r] = m[i];
        m[i] = mr0;
    }

    /**
     * For computing the
     * <a href="https://en.wikipedia.org/wiki/Row_echelon_form">Row Echelon
     * Form</a> of the matrix using
     * <a href="https://en.wikipedia.org/wiki/Gaussian_elimination">Gaussian
     * elimination</a> or returning it if it has already been computed. The
     * first non-zero element in each row is 1 if it exists.
     *
     * @return {@link #ref} computing it first if it is {@code null}.
     */
    public Math_Matrix_BR getRowEchelonForm() {
        if (ref == null) {
            Math_BigRational[][] m = getRows();
            int h = 0;
            /* Initialization of the pivot row */
            int k = 0;
            /* Initialization of the pivot column */
            while (h < rows.length && k < cols.length) {
                /* Find the k-th pivot: */
                int i_max = getMaxRowIndex(m, k, h, rows.length);
                if (m[i_max][k].compareTo(Math_BigRational.ZERO) == 0) {
                    /* No pivot in this column, pass to next column */
                    k++;
                } else {
                    if (h != i_max) {
                        swapRows(m, h, i_max);
                    }
                    /* Do for all rows below pivot: */
                    for (int i = h + 1; i < rows.length; i++) {
                        Math_BigRational f = m[i][k].divide(m[h][k]);
                        /* Fill with zeros the lower part of pivot column: */
                        m[i][k] = Math_BigRational.ZERO;
                        /* Do for all remaining elements in current row: */
                        for (int j = k + 1; j < cols.length; j++) {
                            m[i][j] = m[i][j].subtract(m[h][j].multiply(f));
                        }
                    }
                    /* Increase pivot row and column */
                    h++;
                    k++;
                }
            }
            for (h = rows.length - 1; h >= 0; h--) {
                if (!Math_Matrix_BR.isZeroRow(m, h)) {
                    int i = 0;
                    Math_BigRational v = null;
                    for (int col = 0; col < cols.length; col++) {
                        if (m[h][col].compareTo(Math_BigRational.ZERO) != 0) {
                            i = col;
                            v = m[h][col];
                            break;
                        }
                    }
                    if (v != null) {
                        if (v.compareTo(Math_BigRational.ONE) != 0) {
                            for (int col = i; col < cols.length; col++) {
                                m[h][col] = m[h][col].divide(v);
                            }
                        }
                    }
                }
            }
            ref = new Math_Matrix_BR(m);
        }
        return ref;
    }

    /**
     * For computing the
     * <a href="https://en.wikipedia.org/wiki/Reduced_row_echelon_form">Reduced
     * Row Echelon Form</a> of the matrix using
     * <a href="https://en.wikipedia.org/wiki/Gaussian_elimination">Gaussian
     * elimination</a> or returning it if it has already been computed.
     *
     *
     * @return Get the reduced row echelon form of {@code this}.
     */
    public Math_Matrix_BR getReducedRowEchelonForm() {
        if (rref == null) {
            Math_BigRational[][] m = getRowEchelonForm().getRows();
            for (int r = rows.length - 1; r >= 0; r--) {
                if (!isZeroRow(m, r)) {
                    if (r >= 1) {
                        int c = getColIndexOfSecondNonZeroValue(m, r - 1);
                        if (c > 0) {
                            Math_BigRational d = m[r - 1][c];
                            m[r - 1][c] = Math_BigRational.ZERO;
                            for (int col = c + 1; col < cols.length; col++) {
                                // Subtract from all the rest of the columns
                                m[r - 1][col] = m[r - 1][col].subtract(m[r][col].multiply(d));
                            }
                        } else {
                            break;
                        }
                    } else {
                        int c = getColIndexOfSecondNonZeroValue(m, r);
                        if (c > 0) {
                            Math_BigRational d = m[r][c];
                            m[r][c] = Math_BigRational.ZERO;
                            for (int col = c + 1; col < cols.length; col++) {
                                // Subtract from all the rest of the columns
                                m[r][col] = m[r][col].subtract(m[c][col].multiply(d));
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            rref = new Math_Matrix_BR(m);
        }
        return rref;
    }

    /**
     * @param m The matrix in row major order.
     * @param row The row.
     * @return Get the index of the column in the row of the matrix that has the
     * second non-zero column (counting from the right). If there is no such
     * column, then return {@code -1}.
     */
    public static int getColIndexOfSecondNonZeroValue(Math_BigRational[][] m, int row) {
        boolean firstFound = false;
        for (int col = 0; col < m[0].length - 1; col++) {
            if (m[row][col].compareTo(Math_BigRational.ZERO) != 0) {
                if (firstFound) {
                    return col;
                } else {
                    firstFound = true;
                }
            }
        }
        return -1;
    }

    /**
     * @param m The matrix rows by columns.
     * @param col The col in which to find the row index of the maximum value.
     * @param minrow The minimum row index in which to look.
     * @param maxrow One more than the maximum row index in which to look.
     * @return The row index for the row in m with the largest value in col in
     * the row range [minrow, maxrow).
     */
    public static int getMaxRowIndex(Math_BigRational[][] m, int col, int minrow, int maxrow) {
        int r = minrow;
        Math_BigRational max = m[minrow][col];
        for (int row = minrow; row < maxrow; row++) {
            if (m[row][col].compareTo(max) == 1) {
                max = m[row][col];
                r = row;
            }
        }
        return r;
    }

    /**
     * @param row The row in which to find the column index of the maximum
     * value.
     * @return The column index for the column in row with the largest value.
     */
    public int getMaxColumnIndex(int row) {
        return getMaxColumnIndex(rows, row);
    }

    /**
     * @param m The matrix rows by columns.
     * @param row The row in which to find the column index of the maximum
     * value.
     * @return The column index for the column in row with the largest value.
     */
    public static int getMaxColumnIndex(Math_BigRational[][] m, int row) {
        int c = 0;
        Math_BigRational max = m[row][0];
        for (int col = 0; col < m[0].length; col++) {
            if (m[row][col].compareTo(max) == 1) {
                max = m[row][col];
                c = col;
            }
        }
        return c;
    }

//    public class Vector {
//
//        public Math_BigRational[] v;
//
//        /**
//         * Create a new instance. A deep copy of v is made, so any changes to v
//         * made outside this do not change this.
//         *
//         * @param v A vector used to construct this.
//         */
//        public Vector(Math_BigRational[] v) {
//            this.v = v;
//        }
//
//    }
}
