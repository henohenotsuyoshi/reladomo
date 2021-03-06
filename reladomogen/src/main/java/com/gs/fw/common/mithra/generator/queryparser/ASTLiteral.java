/*
 Copyright 2016 Goldman Sachs.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

/* Generated By:JJTree: Do not edit this line. ASTLiteral.java
    Customized after auto-generation, do not overwrite*/


package com.gs.fw.common.mithra.generator.queryparser;

public class ASTLiteral extends SimpleNode {
    private String value;
    private boolean integer;
    private boolean floatingPoint;
    private boolean string;
    private boolean character;
    private boolean isBoolean;
    private boolean javaLiteral;

    public ASTLiteral(int id)
    {
        super(id);
    }

    public ASTLiteral(MithraQL p, int id)
    {
        super(p, id);
    }

    public ASTLiteral(String value)
    {
        super(0);
        this.setValue(value);
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        if (this.isJavaLiteral())
        {
            this.value = value.substring(1, value.length() - 1);
        }
        else
        {
            this.value = value;
        }
    }

    public boolean isInteger()
    {
        return integer;
    }

    public void setInteger(boolean integer)
    {
        this.integer = integer;
    }

    public boolean isFloatingPoint()
    {
        return floatingPoint;
    }

    public void setFloatingPoint(boolean floatingPoint)
    {
        this.floatingPoint = floatingPoint;
    }

    public boolean isString()
    {
        return string;
    }

    public void setString(boolean string)
    {
        this.string = string;
    }

    public boolean isCharacter()
    {
        return character;
    }

    public void setCharacter(boolean character)
    {
        this.character = character;
    }

    public String toString() {
        return value;
    }

    public boolean isBoolean()
    {
        return isBoolean;
    }

    public void setBoolean(boolean aBoolean)
    {
        isBoolean = aBoolean;
    }

    public boolean isJavaLiteral()
    {
        return javaLiteral;
    }

    public void setJavaLiteral(boolean javaLiteral)
    {
        this.javaLiteral = javaLiteral;
    }

    public String getLiteralString()
    {
//        if (this.isString()) return "\""+this.value+"\"";
//        else return this.value;
        return this.value;
    }

    public String getFinderString()
    {
        return this.getLiteralString();
    }

    /**
     * Accept the visitor. *
     */
    public Object jjtAccept(MithraQLVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public boolean equalsOther(SimpleNode other)
    {
        if (other instanceof ASTLiteral)
        {
            ASTLiteral lit = (ASTLiteral) other;
            return this.isJavaLiteral() == lit.isJavaLiteral() && this.getValue().equals(lit.getValue());
        }
        return false;
    }
}
