grammar mx;
program: (program_comp)+ EOF;
program_comp:class_def
            | func_def
            | variable_def ';'
            ;
class_def: 'class' Identifier '{' (def_type_class|class_constructor|func_def)* '}'';';

def_type_class: type (Identifier ',')* Identifier ';';
class_constructor: Identifier '(' ')' suite;
func_def: type Identifier '(' parameterlist? ')' suite;
parameterlist: (type Identifier',')* type Identifier;

suite: '{' statement* '}';
statement: suite                                                                                            # block_stat
         | expression ';'                                                                                   # exp_stat
         | variable_def ';'                                                                                 # def_stat
         | 'continue' ';'                                                                                   # continue_stat
         | 'break' ';'                                                                                      # break_stat
         | 'return' expression? ';'                                                                         # return_stat
         | 'while' '(' expression ')' statement                                                             # while_stat
         | 'for' '('(init=variable_def)? ';' con=expression? ';' stp=expression? ')' statement              # deffor_stat
         | 'for' '('(init=expression)? ';' con=expression? ';' stp=expression? ')' statement                # expfor_stat
         | 'if' '(' con=expression ')' thenst=statement ('else' elst=statement)?                            # if_stat
         | ';'                                                                                              # empty_stat
         ;
variable_def: type varinit (',' varinit)*;
varinit: Identifier ('=' expression)? ;
expression: Identifier                                                                                          # iden_expr
          | literal                                                                                             # literal_expr
          | expression '.' Identifier '(' func_call_list? ')'                                                   # class_call_expr
          | expression '.' Identifier                                                                           # class_expr
          | expression '[' expression ']'                                                                       # array_expr
          | Identifier '(' func_call_list? ')'                                                                  # func_expr
          | '(' expression ')'                                                                                  # paren_expr
          | <assoc=right> expression op=('++'|'--')                                                             # rightadd_expr
          | op=('++'|'--') expression                                                                           # leftadd_expr
          | <assoc=right> op=('!'|'-'|'~') r=expression                                                         # not_expr
          | new_expression                                                                                      # new_expr
          | l=expression op=('*'|'/'|'%') r=expression                                                          # biexpr
          | l=expression op=('+'|'-') r=expression                                                              # biexpr
          | l=expression op=('<<'|'>>') r=expression                                                            # biexpr
          | l=expression op=('<'|'>'|'<='|'>=') r=expression                                                    # biexpr
          | l=expression op=('=='|'!=') r=expression                                                            # biexpr
          | l=expression op='&' r=expression                                                                    # biexpr
          | l=expression op='^' r=expression                                                                    # biexpr
          | l=expression op='|' r=expression                                                                    # biexpr
          | l=expression op='&&' r=expression                                                                   # biexpr
          | l=expression op='||' r=expression                                                                   # biexpr
          | <assoc=right> expression '?' expression ':' expression                                              # tri_expr
          | <assoc=right> l=expression '=' r=expression                                                         # assign_expr
          ;
new_expression: 'new'  new_type ('(' ')')?;
new_type: basictype ('[' expression ']')+('[' ']')*         # new_basic
        | Identifier (('[' expression ']')+('[' ']')*)?     # new_class
        ;
func_call_list: (expression ',')* expression;

type: basictype ('[' ']')*  # basic_type
    | Identifier ('[' ']')* # class_type
    ;
basictype: BOOL
         | INT
         | STRING
         | VOID
         ;

literal: 'true'
       | 'false'
       | DecimalInteger
       | DoubleString
       | THIS
       | NULL
       ;
VOID: 'void';
BOOL: 'bool';
INT: 'int';
STRING: 'string';

NEW: 'new';
CLASS: 'class';
NULL: 'null';
TRUE: 'true';
FALSE: 'false';
THIS: 'this';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
RETURN: 'return';



LeftParen: '(';
RightParen: ')';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';

Semicolon: ';';
Comma: ',';
Dot: '.';

Plus: '+';
Minus: '-';
Multiply: '*';
Divide: '/';
Mod: '%';

Identifier: [a-zA-Z][a-zA-Z0-9_]*;
DecimalInteger: [1-9][0-9]* | '0';

Whitespace: [ \t]+ -> skip;
Newline: ('\r' '\n'? | '\n') -> skip;
Blockcomment: '/*' .*? '*/' -> skip;
Linecomment: '//' (~[\r\n])* -> skip;


DoubleString: '"' Stringchar* '"';
Stringchar: '\\"'|'\\n'|'\\\\'|[\u0020-\u0021]|[\u0023-\u005B]|[\u005D-\u007E];
// what about strings  "" strings
