package org.yinwang.pysonar.ast;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yinwang.pysonar.Scope;
import org.yinwang.pysonar.types.Type;

/**
 * Represents a keyword argument (name=value) in a function call.
 */
public class Keyword extends Node {

    public String arg;
    @NotNull
    public Node value;


    public Keyword(String arg, @NotNull Node value, int start, int end) {
        super(start, end);
        this.arg = arg;
        this.value = value;
        addChildren(value);
    }

    @NotNull
    @Override
    public Type resolve(Scope s, int tag) {
        return resolveExpr(value, s, tag);
    }

    public String getArg() {
        return arg;
    }

    @NotNull
    public Node getValue() {
        return value;
    }

    @NotNull
    @Override
    public String toString() {
        return "<Keyword:" + arg + ":" + value + ">";
    }

    @NotNull
    @Override
    public String toDisplay() {
        return arg;
    }

    @Override
    public void visit(@NotNull NodeVisitor v) {
        if (v.visit(this)) {
            visitNode(value, v);
        }
    }
}
