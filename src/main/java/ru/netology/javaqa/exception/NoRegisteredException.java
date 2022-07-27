package ru.netology.javaqa.exception;

 public class NoRegisteredException extends RuntimeException {
    public NoRegisteredException(String s) {
        super(s);
    }
}