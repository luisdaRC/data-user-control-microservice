package co.edu.unicartagena.control.application.commands;

public interface Command<R, M> {
    R ejecutar(M command);
}
