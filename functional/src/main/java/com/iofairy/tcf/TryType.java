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
package com.iofairy.tcf;

/**
 * Try Type
 *
 * @since 0.5.0
 */
public enum TryType {
    /** Output an error log with exception Stack Trace when an exception is caught. */
    TRACE_LOG,
    /** Do something when an exception is caught. */
    CATCH_DO,
    /** Output a simple error log when an exception is caught. */
    LOGGING,
    /** Rethrow {@link com.iofairy.except.TryException} when an exception is caught. */
    RETHROW,
    /** Silent processing when an exception is caught. */
    SILENT,
}
