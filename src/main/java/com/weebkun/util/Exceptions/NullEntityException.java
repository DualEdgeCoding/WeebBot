/*
Copyright 2020 Weebkun

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.weebkun.util.Exceptions;

/**
 * Base class for exceptions thrown when getters return null when an entity is expected.
 * exception handlers are required to log messages from thrown exceptions.
 *
 * @author weebkun
 * @version 1.1
 * @since 1.1
 */
public class NullEntityException extends RuntimeException {

    /**
     * type of entity expected.
     */
    String entity;

    /**
     * get entity expected.
     * @return entity - name of entity type
     */
    public String getEntity() {
        return this.entity;
    }

    public NullEntityException(String msg) {
        super(msg);
    }

    public NullEntityException() {
        super("[NullEntityException] Entity expected but found null.");
    }

}
