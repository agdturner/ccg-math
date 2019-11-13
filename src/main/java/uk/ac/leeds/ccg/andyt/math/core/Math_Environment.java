/**
 * Copyright 2019 Andy Turner, The University of Leeds, UK
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package uk.ac.leeds.ccg.andyt.math.core;

import uk.ac.leeds.ccg.andyt.generic.core.Generic_Environment;

/**
 *
 * @author geoagdt
 */
public class Math_Environment  {
    
    public transient final Generic_Environment env;

    public Math_Environment(Generic_Environment e) {
        this.env = e;
    }
}
