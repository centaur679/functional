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


/**
 * An error occurs while executing AI inference, will throw {@code AIInferenceException}. <br>
 * 执行AI推理时出现错误，将抛出此异常
 *
 * @since 0.5.5
 */
public class AIInferenceException extends AIException {

    private static final long serialVersionUID = 997898789653005L;

    public AIInferenceException() {
        super();
    }

    /**
     * Constructs a {@code AIInferenceException} <br>
     * <b>Examples:</b>
     * <blockquote><pre>{@code
     * try {
     *     throw new AIInferenceException("orderId: ${0}, orderName: ${?}, `orderStatus` must be non-empty! ", 10000, "'order_test'");
     * } catch (Exception e) {
     *     assertEquals("orderId: 10000, orderName: 'order_test', `orderStatus` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AIInferenceException("userId: ${_}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AIInferenceException("userId: ${…}, `phone` must be non-empty! ", 10000);
     * } catch (Exception e) {
     *     assertEquals("userId: 10000, `phone` must be non-empty! ", e.getMessage());
     * }
     *
     * try {
     *     throw new AIInferenceException("`orderStatus` must be non-empty! ");
     * } catch (Exception e) {
     *     assertEquals("`orderStatus` must be non-empty! ", e.getMessage());
     * }
     * }</pre></blockquote>
     *
     * @param msgTemplate message template. It is recommended to use any one of <b>{@code ${0}}</b> or <b>{@code ${?}}</b> or <b>{@code ${…}}</b>
     *                    or <b>{@code ${_}}</b> or <b>meaningful names</b> as placeholders
     * @param args        arguments use to fill placeholder
     */
    public AIInferenceException(String msgTemplate, Object... args) {
        super(msgTemplate, args);
    }

    public AIInferenceException(Throwable cause, String msgTemplate, Object... args) {
        super(cause, msgTemplate, args);
    }

    public AIInferenceException(Throwable cause) {
        super(cause);
    }

    @Override
    public AIInferenceException setCode(String code) {
        this.code = code;
        return this;
    }
}