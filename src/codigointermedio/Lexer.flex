package codigointermedio;
import java_cup.runtime.Symbol;
%%
%unicode
%class Lexer
%int
%standalone
%cup
%line
%column
%ignorecase
%state comentario


%{
//ArrayList error_lexico = new ArrayList();
  StringBuffer string = new StringBuffer();

    public int linea()
    { 
        return yyline+1; 
    }

    public int columna()
    { 
        return yycolumn+1;
    }

    private Symbol symbol(int type)
    {
        return new Symbol(type, yyline, yycolumn);
    }


    private Symbol symbol(int type, Object value) 
    {
        return new Symbol(type, yyline, yycolumn, value);
    }

%}


Letra = [a-zA-Z]
Digito = [0-9]+

palabra ={Letra}({Letra}|{Digito}|"_")*

id ={palabra}

fin = ";"
dospuntos = ":"
coma = ","

real = {Digito}"."{Digito}+
espacio=(" "|"\t"|"\n"|"\r")+




%%

<comentario>{

"\n" {yybegin(YYINITIAL);} 
. {}

}


<YYINITIAL>{

{espacio}                  {/*Ignorarlo*/}

"procedure"          {System.out.print("procedure ");
                            return symbol(sym.PROCEDURE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.PROCEDURE, yytext(),yyline+1, yycolumn+1));}

"Type"                  {System.out.print("Type ");
                            return symbol(sym.TYPE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.TYPE, yytext(),yyline+1, yycolumn+1));}


"function"           {System.out.print("function ");
                            return symbol(sym.FUNCTION,
                            new Lexico_Sintactico_Semantico.Tokens(sym.FUNCTION, yytext(),yyline+1, yycolumn+1));}

"if"                 {System.out.print("if ");
                            return symbol(sym.IF,
                            new Lexico_Sintactico_Semantico.Tokens(sym.IF, yytext(),yyline+1, yycolumn+1));}
"boolean"              {System.out.print("boolean \n");
                            return symbol(sym.BOOLEAN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.BOOLEAN, yytext(),yyline+1, yycolumn+1));}


"begin"              {System.out.print("begin \n");
                            return symbol(sym.BEGIN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.BEGIN, yytext(),yyline+1, yycolumn+1));}


"else"               {System.out.print("else ");
                            return symbol(sym.ELSE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.ELSE, yytext(),yyline+1, yycolumn+1));}

"then"               {System.out.print("then ");
                            return symbol(sym.THEN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.THEN, yytext(),yyline+1, yycolumn+1));}

"<"                  {System.out.print("< ");
                            return symbol(sym.MENOR,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MENOR, yytext(),yyline+1, yycolumn+1));}

">"                  {System.out.print("> ");
                            return symbol(sym.MAYOR,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MAYOR, yytext(),yyline+1, yycolumn+1));}

"<="                 {System.out.print("<= ");
                            return symbol(sym.MENORIGUAL,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MENORIGUAL, yytext(),yyline+1, yycolumn+1));}

">="                 {System.out.print(">= ");
                            return symbol(sym.MAYORIGUAL,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MAYORIGUAL, yytext(),yyline+1, yycolumn+1));}

"and"                {System.out.print("and ");
                            return symbol(sym.AND,
                            new Lexico_Sintactico_Semantico.Tokens(sym.AND, yytext(),yyline+1, yycolumn+1));}

"or"                 {System.out.print("or ");
                            return symbol(sym.OR,
                            new Lexico_Sintactico_Semantico.Tokens(sym.OR, yytext(),yyline+1, yycolumn+1));}

"not"                 {System.out.print("not ");
                            return symbol(sym.NOT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.NOT, yytext(),yyline+1, yycolumn+1));}

"/="                 {System.out.print("/= ");
                            return symbol(sym.DISTINTO,
                            new Lexico_Sintactico_Semantico.Tokens(sym.DISTINTO, yytext(),yyline+1, yycolumn+1));}

"while"              {System.out.print("while ");
                            return symbol(sym.WHILE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.WHILE, yytext(),yyline+1, yycolumn+1));}

"exit"               {System.out.print("exit ");
                            return symbol(sym.EXIT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.EXIT, yytext(),yyline+1, yycolumn+1));}

"when"               {System.out.print("when ");
                            return symbol(sym.WHEN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.WHEN, yytext(),yyline+1, yycolumn+1));}

"is"                 {System.out.print("is \n");
                            return symbol(sym.IS,
                            new Lexico_Sintactico_Semantico.Tokens(sym.IS, yytext(),yyline+1, yycolumn+1));}


"end"                {System.out.print("end ");
                            return symbol(sym.END,
                            new Lexico_Sintactico_Semantico.Tokens(sym.END, yytext(),yyline+1, yycolumn+1));}

"Integer"            {System.out.print("Integer ");
                            return symbol(sym.ENTERO,
                            new Lexico_Sintactico_Semantico.Tokens(sym.ENTERO, yytext(),yyline+1, yycolumn+1));}

"Float"     {System.out.print("Float ");
                            return symbol(sym.FLOAT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.FLOAT, yytext(),yyline+1, yycolumn+1));}

"array"     {System.out.print("array ");
                            return symbol(sym.ARRAY,
                            new Lexico_Sintactico_Semantico.Tokens(sym.ARRAY, yytext(),yyline+1, yycolumn+1));}

"of"                 {System.out.print("of ");
                            return symbol(sym.OF,
                            new Lexico_Sintactico_Semantico.Tokens(sym.OF, yytext(),yyline+1, yycolumn+1));}

"false"              {System.out.print("false ");
                            return symbol(sym.FALSE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.FALSE, yytext(),yyline+1, yycolumn+1));}

"true"               {System.out.print("true ");
                            return symbol(sym.TRUE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.TRUE, yytext(),yyline+1, yycolumn+1));}

"return"             {System.out.print("return ");
                            return symbol(sym.RETURN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.RETURN, yytext(),yyline+1, yycolumn+1));}

"newline_"        {System.out.print("newline_ ");
                            return symbol(sym.NEWLINE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.NEWLINE, yytext(),yyline+1, yycolumn+1));}                          

"."                  {System.out.print(". ");
                            return symbol(sym.PUNTO,
                            new Lexico_Sintactico_Semantico.Tokens(sym.PUNTO, yytext(),yyline+1, yycolumn+1));}

"="                  {System.out.print("= ");
                            return symbol(sym.IGUAL,
                            new Lexico_Sintactico_Semantico.Tokens(sym.IGUAL, yytext(),yyline+1, yycolumn+1));}

"\""                 {System.out.print("\" ");
                            return symbol(sym.COMILLA,
                            new Lexico_Sintactico_Semantico.Tokens(sym.COMILLA, yytext(),yyline+1, yycolumn+1));}

"Put"                {System.out.print("Put ");
                            return symbol(sym.PUT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.PUT, yytext(),yyline+1, yycolumn+1));}

"Get"                {System.out.print("Get ");
                            return symbol(sym.GET,
                            new Lexico_Sintactico_Semantico.Tokens(sym.GET, yytext(),yyline+1, yycolumn+1));}

{real}               {System.out.print("REAL ");
                            return symbol(sym.REAL,
                            new Lexico_Sintactico_Semantico.Tokens(sym.REAL, yytext(),yyline+1, yycolumn+1));}

"for"                {System.out.print("for ");
                            return symbol(sym.FOR,
                            new Lexico_Sintactico_Semantico.Tokens(sym.FOR, yytext(),yyline+1, yycolumn+1));}

"in"                 {System.out.print("in ");
                            return symbol(sym.IN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.IN, yytext(),yyline+1, yycolumn+1));}

"out"                {System.out.print("out ");
                            return symbol(sym.OUT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.OUT, yytext(),yyline+1, yycolumn+1));}

"in"{espacio}"out"             {System.out.print("in out ");
                            return symbol(sym.INOUT,
                            new Lexico_Sintactico_Semantico.Tokens(sym.INOUT, yytext(),yyline+1, yycolumn+1));}

"loop"               {System.out.print("loop ");
                            return symbol(sym.LOOP,
                            new Lexico_Sintactico_Semantico.Tokens(sym.LOOP, yytext(),yyline+1, yycolumn+1));}

{Digito}             {System.out.print("NUMERO ");
                            return symbol(sym.NUMERO,
                            new Lexico_Sintactico_Semantico.Tokens(sym.NUMERO, yytext(),yyline+1, yycolumn+1));}

{id}                 {System.out.print("ID ");
                            return symbol(sym.ID,
                            new Lexico_Sintactico_Semantico.Tokens(sym.ID, yytext(),yyline+1, yycolumn+1));}

"+"                  {System.out.print("+ ");
                            return symbol(sym.MAS,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MAS, yytext(),yyline+1, yycolumn+1));}

"-"                  {System.out.print("- ");
                            return symbol(sym.MENOS,
                            new Lexico_Sintactico_Semantico.Tokens(sym.MENOS, yytext(),yyline+1, yycolumn+1));}

"*"                  {System.out.print("* ");
                            return symbol(sym.POR,
                            new Lexico_Sintactico_Semantico.Tokens(sym.POR, yytext(),yyline+1, yycolumn+1));}

"/"                  {System.out.print("/ ");
                            return symbol(sym.ENTRE,
                            new Lexico_Sintactico_Semantico.Tokens(sym.ENTRE, yytext(),yyline+1, yycolumn+1));}

{dospuntos}          {System.out.print(": ");
                            return symbol(sym.DOSPUNTOS,
                            new Lexico_Sintactico_Semantico.Tokens(sym.DOSPUNTOS, yytext(),yyline+1, yycolumn+1));}


{fin}                {System.out.print(";\n");
                            return symbol(sym.FIN,
                            new Lexico_Sintactico_Semantico.Tokens(sym.FIN, yytext(),yyline+1, yycolumn+1));}

{coma}               {System.out.print(", ");
                            return symbol(sym.COMA,
                            new Lexico_Sintactico_Semantico.Tokens(sym.COMA, yytext(),yyline+1, yycolumn+1));}

"("                  {System.out.print("( ");
                            return new Symbol(sym.PAR1,
                            new Lexico_Sintactico_Semantico.Tokens(sym.PAR1, yytext(),yyline+1, yycolumn+1));}

")"                  {System.out.print(") ");
                            return symbol(sym.PAR2,
                            new Lexico_Sintactico_Semantico.Tokens(sym.PAR2, yytext(),yyline+1, yycolumn+1));}

"--"                 {yybegin(comentario);} 


.                    {//System.out.print("ERROR Lexico -> Linea: "+(yyline+1)+", Columna: "+(yycolumn+1)+"\n");
            //interfaz.resultado="ERROR Lexico, no se reconoce el Token de la Linea: "+(yyline+1)+", Columna: "+(yycolumn+1)+"\n";
               // error_lexico.add("ERROR Lexico, no se reconoce el Token de la Linea: "+(yyline+1)+", Columna: "+(yycolumn+1)+"\n");                    }
                }

}
