/*
 * Copyright (C) 2021 iofairy, <https://github.com/io-fairy/functional>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iofairy.except.ai;


import com.iofairy.except.BaseRuntimeException;

/**
 * AI exception base class. <br>
 * AI异常基类
 *
 * @since 0.5.5
 */
public class AIException extends BaseRuntimeException {

    private static final long serialVersionUID = 9999988565600666L;

    public AIException() {
        super();
    }

    /**
     * Constructs a {@code AIException} <br>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public AIException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public AIException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public AIException(Throwable cause) {
        super(cause);
    }

    @Override
    public AIException setCode(String code) {
        this.code = code;
        return this;
    }
}