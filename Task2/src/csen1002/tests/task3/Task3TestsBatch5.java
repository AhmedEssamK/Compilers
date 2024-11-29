package csen1002.tests.task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task3.FallbackDfa;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task3TestsBatch5 {

	@Test
	public void testFallbackDfa1String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#p;q;t;v#0,p,10;0,q,12;0,t,4;0,v,1;1,p,3;1,q,10;1,t,8;1,v,0;2,p,2;2,q,3;2,t,1;2,v,1;3,p,0;3,q,1;3,t,6;3,v,5;4,p,8;4,q,11;4,t,7;4,v,3;5,p,3;5,q,3;5,t,4;5,v,9;6,p,1;6,q,0;6,t,12;6,v,10;7,p,5;7,q,11;7,t,2;7,v,5;8,p,0;8,q,5;8,t,7;8,v,5;9,p,3;9,q,3;9,t,2;9,v,2;10,p,7;10,q,12;10,t,4;10,v,2;11,p,12;11,q,7;11,t,5;11,v,8;12,p,2;12,q,6;12,t,2;12,v,9#12#2;8;11");
		assertEquals("p,2;t,2;qttvqtq,11;p,2", fallbackDfa.run("ptqttvqtqp"));
	}

	@Test
	public void testFallbackDfa1String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#p;q;t;v#0,p,10;0,q,12;0,t,4;0,v,1;1,p,3;1,q,10;1,t,8;1,v,0;2,p,2;2,q,3;2,t,1;2,v,1;3,p,0;3,q,1;3,t,6;3,v,5;4,p,8;4,q,11;4,t,7;4,v,3;5,p,3;5,q,3;5,t,4;5,v,9;6,p,1;6,q,0;6,t,12;6,v,10;7,p,5;7,q,11;7,t,2;7,v,5;8,p,0;8,q,5;8,t,7;8,v,5;9,p,3;9,q,3;9,t,2;9,v,2;10,p,7;10,q,12;10,t,4;10,v,2;11,p,12;11,q,7;11,t,5;11,v,8;12,p,2;12,q,6;12,t,2;12,v,9#12#2;8;11");
		assertEquals("qptqttq,11;tpp,2;qvpt,2;t,2", fallbackDfa.run("qptqttqtppqvptt"));
	}

	@Test
	public void testFallbackDfa1String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#p;q;t;v#0,p,10;0,q,12;0,t,4;0,v,1;1,p,3;1,q,10;1,t,8;1,v,0;2,p,2;2,q,3;2,t,1;2,v,1;3,p,0;3,q,1;3,t,6;3,v,5;4,p,8;4,q,11;4,t,7;4,v,3;5,p,3;5,q,3;5,t,4;5,v,9;6,p,1;6,q,0;6,t,12;6,v,10;7,p,5;7,q,11;7,t,2;7,v,5;8,p,0;8,q,5;8,t,7;8,v,5;9,p,3;9,q,3;9,t,2;9,v,2;10,p,7;10,q,12;10,t,4;10,v,2;11,p,12;11,q,7;11,t,5;11,v,8;12,p,2;12,q,6;12,t,2;12,v,9#12#2;8;11");
		assertEquals("qtqqpqtpp,2;tvqqpp,2;vvp,2;t,2", fallbackDfa.run("qtqqpqtpptvqqppvvpt"));
	}

	@Test
	public void testFallbackDfa1String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#p;q;t;v#0,p,10;0,q,12;0,t,4;0,v,1;1,p,3;1,q,10;1,t,8;1,v,0;2,p,2;2,q,3;2,t,1;2,v,1;3,p,0;3,q,1;3,t,6;3,v,5;4,p,8;4,q,11;4,t,7;4,v,3;5,p,3;5,q,3;5,t,4;5,v,9;6,p,1;6,q,0;6,t,12;6,v,10;7,p,5;7,q,11;7,t,2;7,v,5;8,p,0;8,q,5;8,t,7;8,v,5;9,p,3;9,q,3;9,t,2;9,v,2;10,p,7;10,q,12;10,t,4;10,v,2;11,p,12;11,q,7;11,t,5;11,v,8;12,p,2;12,q,6;12,t,2;12,v,9#12#2;8;11");
		assertEquals("qvptqqt,8;vt,2;tvvqttvptq,11;q,6", fallbackDfa.run("qvptqqtvttvvqttvptqq"));
	}

	@Test
	public void testFallbackDfa1String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9;10;11;12#p;q;t;v#0,p,10;0,q,12;0,t,4;0,v,1;1,p,3;1,q,10;1,t,8;1,v,0;2,p,2;2,q,3;2,t,1;2,v,1;3,p,0;3,q,1;3,t,6;3,v,5;4,p,8;4,q,11;4,t,7;4,v,3;5,p,3;5,q,3;5,t,4;5,v,9;6,p,1;6,q,0;6,t,12;6,v,10;7,p,5;7,q,11;7,t,2;7,v,5;8,p,0;8,q,5;8,t,7;8,v,5;9,p,3;9,q,3;9,t,2;9,v,2;10,p,7;10,q,12;10,t,4;10,v,2;11,p,12;11,q,7;11,t,5;11,v,8;12,p,2;12,q,6;12,t,2;12,v,9#12#2;8;11");
		assertEquals("ptt,8;t,2;vv,2;qq,0", fallbackDfa.run("ptttvvqq"));
	}

	@Test
	public void testFallbackDfa2String1() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#a;k;p;r;u#0,a,8;0,k,0;0,p,7;0,r,5;0,u,2;1,a,8;1,k,0;1,p,2;1,r,4;1,u,0;2,a,5;2,k,0;2,p,9;2,r,3;2,u,7;3,a,3;3,k,9;3,p,8;3,r,7;3,u,6;4,a,0;4,k,1;4,p,3;4,r,4;4,u,9;5,a,5;5,k,9;5,p,7;5,r,6;5,u,0;6,a,9;6,k,0;6,p,5;6,r,8;6,u,6;7,a,6;7,k,7;7,p,8;7,r,9;7,u,9;8,a,7;8,k,5;8,p,1;8,r,4;8,u,5;9,a,5;9,k,1;9,p,7;9,r,3;9,u,4#4#1;6;8");
		assertEquals("rraauuupk,1;k,1;pkruu,6;ap,7", fallbackDfa.run("rraauuupkkpkruuap"));
	}

	@Test
	public void testFallbackDfa2String2() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#a;k;p;r;u#0,a,8;0,k,0;0,p,7;0,r,5;0,u,2;1,a,8;1,k,0;1,p,2;1,r,4;1,u,0;2,a,5;2,k,0;2,p,9;2,r,3;2,u,7;3,a,3;3,k,9;3,p,8;3,r,7;3,u,6;4,a,0;4,k,1;4,p,3;4,r,4;4,u,9;5,a,5;5,k,9;5,p,7;5,r,6;5,u,0;6,a,9;6,k,0;6,p,5;6,r,8;6,u,6;7,a,6;7,k,7;7,p,8;7,r,9;7,u,9;8,a,7;8,k,5;8,p,1;8,r,4;8,u,5;9,a,5;9,k,1;9,p,7;9,r,3;9,u,4#4#1;6;8");
		assertEquals("aapa,8;k,1;uk,1;k,1;p,3", fallbackDfa.run("aapakukkp"));
	}

	@Test
	public void testFallbackDfa2String3() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#a;k;p;r;u#0,a,8;0,k,0;0,p,7;0,r,5;0,u,2;1,a,8;1,k,0;1,p,2;1,r,4;1,u,0;2,a,5;2,k,0;2,p,9;2,r,3;2,u,7;3,a,3;3,k,9;3,p,8;3,r,7;3,u,6;4,a,0;4,k,1;4,p,3;4,r,4;4,u,9;5,a,5;5,k,9;5,p,7;5,r,6;5,u,0;6,a,9;6,k,0;6,p,5;6,r,8;6,u,6;7,a,6;7,k,7;7,p,8;7,r,9;7,u,9;8,a,7;8,k,5;8,p,1;8,r,4;8,u,5;9,a,5;9,k,1;9,p,7;9,r,3;9,u,4#4#1;6;8");
		assertEquals("kukkuaar,6;k,1;krauruu,6;k,1", fallbackDfa.run("kukkuaarkkrauruuk"));
	}

	@Test
	public void testFallbackDfa2String4() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#a;k;p;r;u#0,a,8;0,k,0;0,p,7;0,r,5;0,u,2;1,a,8;1,k,0;1,p,2;1,r,4;1,u,0;2,a,5;2,k,0;2,p,9;2,r,3;2,u,7;3,a,3;3,k,9;3,p,8;3,r,7;3,u,6;4,a,0;4,k,1;4,p,3;4,r,4;4,u,9;5,a,5;5,k,9;5,p,7;5,r,6;5,u,0;6,a,9;6,k,0;6,p,5;6,r,8;6,u,6;7,a,6;7,k,7;7,p,8;7,r,9;7,u,9;8,a,7;8,k,5;8,p,1;8,r,4;8,u,5;9,a,5;9,k,1;9,p,7;9,r,3;9,u,4#4#1;6;8");
		assertEquals("kkkrkauraapkp,8;arr,6;k,1;pr,7", fallbackDfa.run("kkkrkauraapkparrkpr"));
	}

	@Test
	public void testFallbackDfa2String5() {
		FallbackDfa fallbackDfa= new FallbackDfa("0;1;2;3;4;5;6;7;8;9#a;k;p;r;u#0,a,8;0,k,0;0,p,7;0,r,5;0,u,2;1,a,8;1,k,0;1,p,2;1,r,4;1,u,0;2,a,5;2,k,0;2,p,9;2,r,3;2,u,7;3,a,3;3,k,9;3,p,8;3,r,7;3,u,6;4,a,0;4,k,1;4,p,3;4,r,4;4,u,9;5,a,5;5,k,9;5,p,7;5,r,6;5,u,0;6,a,9;6,k,0;6,p,5;6,r,8;6,u,6;7,a,6;7,k,7;7,p,8;7,r,9;7,u,9;8,a,7;8,k,5;8,p,1;8,r,4;8,u,5;9,a,5;9,k,1;9,p,7;9,r,3;9,u,4#4#1;6;8");
		assertEquals("pukpraaaupp,8;k,1;k,1;ur,3", fallbackDfa.run("pukpraaauppkkur"));
	}

}