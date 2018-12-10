/**
 * Copyright 2010 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.math;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

public class Generic_int
        extends Generic_Number
        implements Serializable {

    static final long serialVersionUID = 1L;

    /** Creates a new instance of Generic_BigDecimal */
    public Generic_int() {
        //super();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Generic_int().test();
    }

    private void test() {
    }

    public static boolean isEven(int x) {
        return x % 2 == 0;
    }
}
