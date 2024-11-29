package csen1002.tests.task1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task1.RegExToNfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task1TestsBatch5 {

	@Test
	public void testRegEx1() {
		RegExToNfa regExToNfa= new RegExToNfa("d;f;k;t#tdd*|k.f||*");
		assertEquals("0;1;2;3;4;5;6;7;8;9;11;12;13;14;15;16;17;18;19#d;f;k;t#0,t,1;1,e,17;2,d,3;3,e,9;4,d,5;5,e,4;5,e,7;6,e,4;6,e,7;7,e,9;8,e,2;8,e,6;9,k,11;11,e,15;12,f,13;13,e,15;14,e,8;14,e,12;15,e,17;16,e,0;16,e,14;17,e,16;17,e,19;18,e,16;18,e,19#18#19", regExToNfa.toString());
	}

	@Test
	public void testRegEx2() {
		RegExToNfa regExToNfa= new RegExToNfa("a;q;w;x#qxax|w.*||");
		assertEquals("0;1;2;3;4;5;6;7;8;9;11;12;13;14;15;16;17#a;q;w;x#0,q,1;1,e,17;2,x,3;3,e,15;4,a,5;5,e,9;6,x,7;7,e,9;8,e,4;8,e,6;9,w,11;11,e,8;11,e,13;12,e,8;12,e,13;13,e,15;14,e,2;14,e,12;15,e,17;16,e,0;16,e,14#16#17", regExToNfa.toString());
	}

	@Test
	public void testRegEx3() {
		RegExToNfa regExToNfa= new RegExToNfa("k;t;u;y#ktyu..k..");
		assertEquals("0;1;3;5;7;9#k;t;u;y#0,k,1;1,t,3;3,y,5;5,u,7;7,k,9#0#9", regExToNfa.toString());
	}

	@Test
	public void testRegEx4() {
		RegExToNfa regExToNfa= new RegExToNfa("f;i;k;n;p#kpi|f**|.n|");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11;12;13;15;16;17;18;19#f;i;k;n;p#0,k,1;1,e,6;1,e,12;2,p,3;3,e,7;4,i,5;5,e,7;6,e,2;6,e,4;7,e,15;8,f,9;9,e,8;9,e,11;10,e,8;10,e,11;11,e,10;11,e,13;12,e,10;12,e,13;13,e,15;15,e,19;16,n,17;17,e,19;18,e,0;18,e,16#18#19", regExToNfa.toString());
	}

	@Test
	public void testRegEx5() {
		RegExToNfa regExToNfa= new RegExToNfa("a;l#ll.l.ae.*|");
		assertEquals("0;1;3;5;6;7;9;10;11;12;13#a;l#0,l,1;1,l,3;3,l,5;5,e,13;6,a,7;7,e,9;9,e,6;9,e,11;10,e,6;10,e,11;11,e,13;12,e,0;12,e,10#12#13", regExToNfa.toString());
	}

	@Test
	public void testRegEx6() {
		RegExToNfa regExToNfa= new RegExToNfa("b;f;l;p;y#lfy|.p.b.");
		assertEquals("0;1;2;3;4;5;7;9;11#b;f;l;p;y#0,l,1;1,e,2;1,e,4;2,f,3;3,e,7;4,y,5;5,e,7;7,p,9;9,b,11#0#11", regExToNfa.toString());
	}

	@Test
	public void testRegEx7() {
		RegExToNfa regExToNfa= new RegExToNfa("h;m;q;t;w#qmt|w|t|.h.");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11;12;13;15;17#h;m;q;t;w#0,q,1;1,e,10;1,e,12;2,m,3;3,e,7;4,t,5;5,e,7;6,e,2;6,e,4;7,e,11;8,w,9;9,e,11;10,e,6;10,e,8;11,e,15;12,t,13;13,e,15;15,h,17#0#17", regExToNfa.toString());
	}

	@Test
	public void testRegEx8() {
		RegExToNfa regExToNfa= new RegExToNfa("f;g;k;m;t#ggm.|tkf.|.");
		assertEquals("0;1;2;3;5;6;7;8;9;10;11;13;15#f;g;k;m;t#0,g,1;1,e,7;2,g,3;3,m,5;5,e,7;6,e,0;6,e,2;7,e,8;7,e,10;8,t,9;9,e,15;10,k,11;11,f,13;13,e,15#6#15", regExToNfa.toString());
	}

	@Test
	public void testRegEx9() {
		RegExToNfa regExToNfa= new RegExToNfa("f;s;t#s*t*|ff|e|.");
		assertEquals("0;1;2;3;4;5;6;7;8;9;10;11;12;13;14;15;16;17;19#f;s;t#0,s,1;1,e,0;1,e,3;2,e,0;2,e,3;3,e,9;4,t,5;5,e,4;5,e,7;6,e,4;6,e,7;7,e,9;8,e,2;8,e,6;9,e,14;9,e,16;10,f,11;11,e,15;12,f,13;13,e,15;14,e,10;14,e,12;15,e,19;16,e,17;17,e,19#8#19", regExToNfa.toString());
	}

	@Test
	public void testRegEx10() {
		RegExToNfa regExToNfa= new RegExToNfa("r;z#rzzr..|e.");
		assertEquals("0;1;2;3;5;7;8;9;11#r;z#0,r,1;1,e,9;2,z,3;3,z,5;5,r,7;7,e,9;8,e,0;8,e,2;9,e,11#8#11", regExToNfa.toString());
	}

}