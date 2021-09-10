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

import ch.obermuhlner.math.big.BigRational;
import java.util.Arrays;

/**
 * For processing matrices holding BigRational numbers.
 * 
 * @author Andy Turner
 * @version 1.0
 */
public class Math_Matrix_BR {

    /**
     * The number of rows in the matrix
     */
    protected final int nr;

    /**
     * The number of columns in the matrix
     */
    protected final int nc;

    /**
     * The matrix. With rows then columns
     */
    protected final BigRational[][] m;

    /**
     * Create a new instance.
     * 
     * @param nr What {@link #nr} is set to.
     * @param nc What {@link #nc} is set to.
     */
    public Math_Matrix_BR(int nr, int nc) {
        m = new BigRational[nr][nc];
        this.nr = nr;
        this.nc = nc;
    }

    @Override
    public String toString() {
        String r = this.getClass().getSimpleName() + "(nr=" + nr + ", nc=" + nc
                + "\n";
        for (int row = 0; row < nr; row++) {
            for (int col = 0; col < nc; col++) {
                r += "" + m[row][col] + " ";
            }
            r += "\n";
        }
        r += ")";
        return r;
    }

    /**
     * @return {@link #m}
     */
    public BigRational[][] getM() {
        return m;
    }

    /**
     * @return {@link #nr}
     */
    public int getNr() {
        return nr;
    }

    /**
     * @return {@link #nc}
     */
    public int getNc() {
        return nc;
    }

    /**
     * https://en.wikipedia.org/wiki/Matrix_multiplication
     *
     * @param b The matrix to multiply {@code this} by.
     * @return result of multiplying {@code this} by {@code b}
     */
    public Math_Matrix_BR multiply(Math_Matrix_BR b) {
        return multiply(this, b);
    }

    /**
     * https://en.wikipedia.org/wiki/Matrix_multiplication
     *
     * @param a The matrix to multiply.
     * @param b The matrix to multiply by.
     * @return result of multiplying {@code a} by {@code b}
     */
    public static Math_Matrix_BR multiply(Math_Matrix_BR a, Math_Matrix_BR b) {
        Math_Matrix_BR r = null;
        if (a.nc == b.nr) {
            r = new Math_Matrix_BR(a.nr, b.nc);
            for (int row = 0; row < r.nr; row++) {
                for (int col = 0; col < r.nc; col++) {
                    BigRational v = BigRational.ZERO;
                    for (int i = 0; i < b.nr; i++) {
                        v = v.add(a.m[row][i].multiply(b.m[i][col]));
                    }
                    r.m[row][col] = v;
                }
            }
        }
        return r;
    }

    /**
     * https://en.wikipedia.org/wiki/Determinant
     * Calculates and returns the determinant of {@code this}.
     * @return The calculated determinant of {@code this}.
     */
    public BigRational getDeterminant() {
        if (nr == nc) {
            return getDeterminant(m);
        } else {
            throw new RuntimeException("Cannot calculate determinant of matrix "
                    + "as it is not square.");
        }
    }

    private BigRational getDeterminant(BigRational[][] m) {
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
                BigRational r = BigRational.ZERO;
                for (int i = 0; i < m.length; i++) {
                    BigRational sr = m[0][i].multiply(getDeterminant(
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
    
    private BigRational[][] getSubMatrix(BigRational[][] m, int col) {
        int len = m.length;
        int len2 = len - 1;
        int col2 = col + 1;
        int r = 0;
        BigRational[][] res = new BigRational[len2][len2];
        for (int i = 1; i < len; i++) {
            int c = 0;
            for (int j = 0; j < col; j++) {
                res[r][c] = m[i][j];
                c ++;
            }
            for (int j = col2; j < len; j++) {
                res[r][c] = m[i][j];
                c ++;
            }
            r ++;
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
        Math_Matrix_BR res = new Math_Matrix_BR(size, size);
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                res.m[r][c] = BigRational.ZERO;
            }
        }
        for (int r = 0; r < size; r++) {
            res.m[r][r] = BigRational.ONE;
        }
        return res;
    }

    /**
     * https://en.wikipedia.org/wiki/Transpose
     *
     * @return {@code this} transposed
     */
    public Math_Matrix_BR transpose() {
        return transpose(this);
    }

    /**
     * https://en.wikipedia.org/wiki/Transpose
     *
     * @param a The matrix to return the transpose of.
     * @return {@code a} transposed
     */
    public static Math_Matrix_BR transpose(Math_Matrix_BR a) {
        Math_Matrix_BR r = new Math_Matrix_BR(a.nc, a.nr);
        for (int row = 0; row < a.nr; row++) {
            for (int col = 0; col < a.nc; col++) {
                r.m[col][row] = a.m[row][col];
            }
        }
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
        int hash = 7;
        hash = 71 * hash + this.nr;
        hash = 71 * hash + this.nc;
        hash = 71 * hash + Arrays.deepHashCode(this.m);
        return hash;
    }

    /**
     * @param m The Math_Matrix_BR to test for equality with this.
     * @return {@code true} iff this is equal to {@code m}
     */
    public boolean equals(Math_Matrix_BR m) {
        if (nr == m.nr && nc == m.nc) {
            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    if (this.m[r][c].compareTo(m.m[r][c]) != 0) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}
