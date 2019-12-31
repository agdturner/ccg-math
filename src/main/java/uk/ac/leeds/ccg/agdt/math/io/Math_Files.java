/*
 * Copyright 2019 Centre for Computational Geography, University of Leeds.
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
package uk.ac.leeds.ccg.agdt.math.io;

import java.io.IOException;
import uk.ac.leeds.ccg.generic.io.Generic_Defaults;
import uk.ac.leeds.ccg.generic.io.Generic_Files;
import uk.ac.leeds.ccg.agdt.math.core.Math_Strings;

/**
 * Math Files.
 * 
 * @author Andy Turner
 * @version 1.0.0
 */
public class Math_Files extends Generic_Files {
    
    /**
     * @param d The Generic_Defaults.
     * @throws java.io.IOException If encountered.
     */
    public Math_Files(Generic_Defaults d) throws IOException {
        super(d);
    }
}
