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

import com.iofairy.lambda.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Support some simple try-catch block<br>
 * 仅支持一些简单的try-catch块<br><br>
 * <b>Examples:</b><br>
 * non-use of <b>{@code Try}</b>: <br>
 * <pre>
 *      try {
 *          Thread.sleep(500);
 *      } catch (InterruptedException e) {
 *          e.printStackTrace();
 *      }
 * </pre>
 * use <b>{@code Try}</b>: <br>
 * <pre>
 *      Try.tcf(() -&gt; Thread.sleep(500));
 * </pre>
 * @since 0.0.4
 */
public class Try {

    private final static Logger log = Logger.getLogger(Try.class.getName());

    private Try() {
    }

    public static void tcf(VT0<Throwable> tryAction) {
        try {
            tryAction.$();
        } catch (Throwable e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            log.severe("Exception in `run` method:\n\n" + sw.getBuffer().toString());
        }
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction) {
        try {
            return tryAction.$();
        } catch (Throwable e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            log.severe("Exception in `run` method:\n\n" + sw.getBuffer().toString());
        }
        return null;
    }

    public static void tcf(VT0<Throwable> tryAction, V1<Throwable> catchAction) {
        try {
            tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
    }

    public static <R> R tcf(RT0<R, Throwable> tryAction, V1<Throwable> catchAction) {
        try {
            return tryAction.$();
        } catch (Throwable e) {
            if (catchAction != null) catchAction.$(e);
        }
        return null;
    }

}