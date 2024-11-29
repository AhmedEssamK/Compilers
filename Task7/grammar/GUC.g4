grammar GUC;

test : (ID | USERNAME)+;
EMAIL: USERNAME DOMAIN EG ;
USERNAME: [a-zA-Z]+ '.' [a-zA-Z-]+;
ID: NONZERODIGIT DIGIT? '-' DIGIT DIGIT DIGIT DIGIT DIGIT? DIGIT?;
fragment DIGIT: [0-9];
fragment NONZERODIGIT: [1-9];
fragment EG: [Ee][Gg];
fragment DOMAIN options{caseInsensitive =true;}: '@student.guc.edu.';
WS: [ \n\t\r]+ -> skip;