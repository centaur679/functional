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
package com.iofairy.except;

import com.iofairy.si.SI;

/**
 * Base RuntimeException. <br>
 * 自定义的运行时异常基类
 *
 * @since 0.5.5
 */
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 99985638568632666L;

    /**
     * error code
     */
    protected String code;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BaseRuntimeException() {
        super();
    }

    /**
     * Constructs a {@code BaseRuntimeException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new BaseRuntimeException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new BaseRuntimeException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new BaseRuntimeException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new BaseRuntimeException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public BaseRuntimeException(String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args));
    }

    public BaseRuntimeException(Throwable cause, String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args), cause);
    }

    public BaseRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new runtime exception with the specified detail message, cause, suppression enabled or disabled,
     * and writable stack trace enabled or disabled.
     *
     * @param cause              the cause.  (A {@code null} value is permitted, and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     * @param msgTemplate        message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                           or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args               arguments use to fill placeholder
     */
    public BaseRuntimeException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, String msgTemplate, Object... args) {
        super(getMsg(msgTemplate, args), cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return code;
    }

    public BaseRuntimeException setCode(String code) {
        this.code = code;
        return this;
    }

    private static String getMsg(String msgTemplate, Object... args) {
        if (msgTemplate == null) return null;
        return SI.$(msgTemplate, args);
    }

}