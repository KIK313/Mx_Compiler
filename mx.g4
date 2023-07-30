grammar mx;
program: (class_def|func_def|(variable_def';'))* main_fn (class_def|func_def|(variable_def';'))* EOF;
class_def: 'class' Identifier '{' class_comp* '}'';';
class_comp: def_type_class      # class_def_variable
          | class_constructor   # class_builder
          | func_def            # class_func
          ;
def_type_class: type (Identifier ',')* Identifier ';';
class_constructor: Identifier '(' ')' suite;
func_def: return_type Identifier '(' parameterlist? ')' suite;
parameterlist: (type Identifier',')* type Identifier;

main_fn:'int' 'main' '('')' suite;
suite: '{' statement* '}';
statement: suite                                                                                # block_statement
         | expression ';'                                                                       # exp_statement
         | variable_def ';'                                                                     # def_statement
         | 'continue' ';'                                                                       # continue_statement
         | 'break' ';'                                                                          # break_statement
         | 'return' expression? ';'                                                             # return_statement
         | 'while' '(' expression ')' statement                                                 # while_statement
         | 'for' '('(variable_def | expression)? ';' expression? ';' expression? ')' statement  # for_statement
         | 'if' '(' expression ')' statement ('else' statement)?                                # if_statement
         | ';'                                                                                  # empty_statement
         ;
variable_def: type Identifier ('=' expression)? (',' Identifier ('=' expression)?)*;
expression: Identifier                                                                                          # Iden_expr
          | literal                                                                                             # literal_expr
          | Identifier ('['expression']')* ('.' Identifier ('(' func_call_list? ')'| ('['expression']')*))+     # class_expr
          | expression '[' expression ']'                                                                       # array_expr
          | Identifier '(' func_call_list? ')'                                                                  # func_expr
          | '(' expression ')'                                                                                  # paren_expr
          | <assoc=right> expression op=('++'|'--')                                                             # rightadd_expr
          | op=('++'|'--') expression                                                                           # leftadd_expr
          | <assoc=right> op=('!'|'-'|'~') r=expression                                                         # not_expr
          | new_expression                                                                                      # new_expr
          | l=expression op=('*'|'/'|'%') r=expression                                                          # mul_expr
          | l=expression op=('+'|'-') r=expression                                                              # add_expr
          | l=expression op=('<<'|'>>') r=expression                                                            # shift_expr
          | l=expression op=('<'|'>'|'<='|'>=') r=expression                                                    # isbig_expr
          | l=expression op=('=='|'!=') r=expression                                                            # iseq_expr
          | l=expression '&' r=expression                                                                       # bitand_expr
          | l=expression '^' r=expression                                                                       # bixor_expr
          | l=expression '|' r=expression                                                                       # bitor_expr
          | l=expression '&&' r=expression                                                                      # and_expr
          | l=expression '||' r=expression                                                                      # or_expr
          | expression '?' expression ':' expression                                                            # tri_expr
          | <assoc=right> l=expression '=' r=expression                                                         # assign_expr
          ;
new_expression: 'new'  new_type ('(' ')')?;
new_type: (basictype|Identifier) (('[' expression ']')+('[' ']')*)?;
func_call_list: (expression ',')* expression;
lhs: Identifier             # lhsid
   | lhs '[' expression ']' # lhsarray
   | lhs '.' Identifier     # lhsdot
   | '++' lhs               # lhsadd
   | '--' lhs               # lhsminus
   ;
return_type: VOID   # void_return
           | type   # value_return
           ;
type: basictype         # basic_type
    | Identifier        # class_type
    | type ('[' ']')+   # array_type
    ;
basictype: BOOL     # type_bool
         | INT      # type_int
         | STRING   # type_string
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
