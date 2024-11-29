/**
 * Write your info here
 *
 * @name Ahmed Essam
 * @id 49-2174
 * @labNumber 10
 */

grammar Task7;

/**
 * This rule is to check your grammar using "ANTLR Preview"
 */
test: /* (Rule1 | Rule2 | ... | RuleN)+ */ ONE | ZERO | ERROR EOF; //Replace the non-fragment lexer rules here

// Write all the necessary lexer rules and fragment lexer rules here


ZERO: '001' | '010' | '101' | '110' | '100';
ONE: '000' | '011' | '111';
ERROR: [0-1] | [0-1][0-1];