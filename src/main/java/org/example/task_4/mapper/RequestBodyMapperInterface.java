package org.example.task_4.mapper;

public interface RequestBodyMapperInterface<F,T> {
    T map(F request);
}

