package com.rpg.app.loader;

/**
 * Created by Valeriy Shtanko on 2017-Nov-19, 00:36
 */
public class GameLoaderException extends Exception {
    public GameLoaderException(String message) {
        super(message);
    }

    public GameLoaderException(String format, Object... args) {
        super(String.format(format, args));
    }

    public GameLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
