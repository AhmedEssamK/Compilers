package csen1002.tests.task4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import csen1002.main.task4.CfgEpsUnitElim;

@Timeout(value = 5, unit = TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
public class Task4TestsBatch5 {

	@Test
	public void testCfgEpsilonRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;Z;R;L;B;P#c;l;t;u#S/RZc,cBPZP;Z/B,ScZLl,cZZtR,e;R/LPBcL,e,lBB,lLt,tLZuR;L/PLRtS,PR,S,tBc;B/BcBB,BlL,L,P,S,e;P/PPl,PuPLc,ZRRP");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;Z;R;L;B;P#c;l;t;u#S/RZc,Rc,Zc,c,cBPP,cBPZP,cPP,cPZP;Z/B,ScLl,ScZLl,cZZt,cZZtR,cZt,cZtR,ct,ctR;R/LPBcL,LPcL,l,lB,lBB,lLt,tLZu,tLZuR,tLu,tLuR;L/P,PLRtS,PLtS,PR,S,tBc,tc;B/Bc,BcB,BcBB,BlL,L,P,S,c,cB,cBB,lL;P/P,PPl,PuPLc,RP,RRP,ZP,ZRP,ZRRP", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;O;B;J;K;G;U#m;p;u;y;z#S/SJ,z,zJuU,zSUm;O/B,G,S,e,pOJz,yJuK;B/BSzO,S,U,yBz,zO;J/G,zOOKU,zUG;K/G,S,mBmOU;G/GpU,JyS,Km,O,e,z;U/J,uBJSB");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;O;B;J;K;G;U#m;p;u;y;z#S/S,SJ,z,zJu,zJuU,zSUm,zSm,zu,zuU;O/B,G,S,pJz,pOJz,pOz,pz,yJu,yJuK,yu,yuK;B/BSz,BSzO,S,Sz,SzO,U,yBz,yz,z,zO;J/G,z,zG,zK,zKU,zO,zOK,zOKU,zOO,zOOK,zOOKU,zOOU,zOU,zU,zUG;K/G,S,mBm,mBmO,mBmOU,mBmU,mm,mmO,mmOU,mmU;G/Gp,GpU,JyS,Km,O,m,p,pU,yS,z;U/J,uBJS,uBJSB,uBS,uBSB,uJS,uJSB,uS,uSB", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;H;G;X;K;D;W#f;o;s#S/DWSs,GDf,HoHWS;H/DoGf,HoHGS,e,fKfWf;G/e,f,oXf,sGoGX,sSXK;X/DXX,GoGfS,XoSf,e,sW;K/H,K,SfGsS,X,XSKH,sSsSs;D/GK,H,HGGKo,WS,fHsD;W/XD,XoWf,fHWsS");
		cfgEpsUnitElim.eliminateEpsilonRules();
		assertEquals("S;H;G;X;K;D;W#f;o;s#S/DSs,DWSs,Df,GDf,Gf,HoHS,HoHWS,HoS,HoWS,Ss,WSs,f,oHS,oHWS,oS,oWS;H/DoGf,Dof,HoGS,HoHGS,HoHS,HoS,fKfWf,fKff,ffWf,fff,oGS,oGf,oHGS,oHS,oS,of;G/f,oXf,of,sGo,sGoG,sGoGX,sGoX,sS,sSK,sSX,sSXK,so,soG,soGX,soX;X/D,DX,DXX,GoGfS,GofS,X,XX,XoSf,oGfS,oSf,ofS,s,sW;K/H,K,S,SH,SK,SKH,SfGsS,SfsS,X,XS,XSH,XSK,XSKH,sSsSs;D/G,GGKo,GGo,GK,GKo,Go,H,HGGKo,HGGo,HGKo,HGo,HKo,Ho,K,Ko,S,WS,fHs,fHsD,fs,fsD,o;W/D,X,XD,XoWf,Xof,fHWsS,fHsS,fWsS,fsS,oWf,of", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;K;I;F;A#m;q;z#S/A,qKqK,zKq;K/F,IKFAq,Im,KqASq,S,qKA;I/AK,AqKII,F,IAq,K,zS;F/F,I,KSKS,mFFmK,qA;A/AFFI,SKqI,zIS,zIm");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;K;I;F;A#m;q;z#S/AFFI,SKqI,qKqK,zIS,zIm,zKq;K/AFFI,AK,AqKII,IAq,IKFAq,Im,KSKS,KqASq,SKqI,mFFmK,qA,qKA,qKqK,zIS,zIm,zKq,zS;I/AFFI,AK,AqKII,IAq,IKFAq,Im,KSKS,KqASq,SKqI,mFFmK,qA,qKA,qKqK,zIS,zIm,zKq,zS;F/AFFI,AK,AqKII,IAq,IKFAq,Im,KSKS,KqASq,SKqI,mFFmK,qA,qKA,qKqK,zIS,zIm,zKq,zS;A/AFFI,SKqI,zIS,zIm", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;C;U;O;W;A#f;w;x#S/C,SSS,wS,xSC;C/C,fS,fW,wA,xWwOS;U/AU,OOfAS,U,WSA,xCU;O/S,W,fU,xO,xUOOW;W/O,OfUW,UxOwS;A/S,U,xUOwO");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;C;U;O;W;A#f;w;x#S/SSS,fS,fW,wA,wS,xSC,xWwOS;C/fS,fW,wA,xWwOS;U/AU,OOfAS,WSA,xCU;O/OfUW,SSS,UxOwS,fS,fU,fW,wA,wS,xO,xSC,xUOOW,xWwOS;W/OfUW,SSS,UxOwS,fS,fU,fW,wA,wS,xO,xSC,xUOOW,xWwOS;A/AU,OOfAS,SSS,WSA,fS,fW,wA,wS,xCU,xSC,xUOwO,xWwOS", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;P;O;J;I;Q#d;f;m;n;w#S/ISmQP,J,OJ,SdInP,dP;P/IIIP,J,O,OQ,SOPmI,SOwP;O/I,OQIJ,S,fSn,mPP;J/JOS,Q,SmS,SnJn;I/IPIf,O,P,PfJ;Q/If,P,nS,nSwQ");
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;P;O;J;I;Q#d;f;m;n;w#S/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ;P/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ;O/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ;J/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ;I/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ;Q/IIIP,IPIf,ISmQP,If,JOS,OJ,OQ,OQIJ,PfJ,SOPmI,SOwP,SdInP,SmS,SnJn,dP,fSn,mPP,nS,nSwQ", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination1() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;J;B;Z;R#a;c;d;l;m#S/RZBJ,ZRS;J/B,J,SBl,e;B/J,R,RSBB,S,Z,d,mR;Z/Bl,JdJ,RcRaZ,e;R/SR,mR");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;J;B;Z;R#a;c;d;l;m#S/RB,RBJ,RJ,RS,RZ,RZB,RZBJ,RZJ,SR,ZRS,mR;J/Bl,Jd,JdJ,RB,RBJ,RJ,RS,RSB,RSBB,RZ,RZB,RZBJ,RZJ,RcRa,RcRaZ,SBl,SR,Sl,ZRS,d,dJ,l,mR;B/Bl,Jd,JdJ,RB,RBJ,RJ,RS,RSB,RSBB,RZ,RZB,RZBJ,RZJ,RcRa,RcRaZ,SBl,SR,Sl,ZRS,d,dJ,l,mR;Z/Bl,Jd,JdJ,RcRa,RcRaZ,d,dJ,l;R/SR,mR", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination2() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;U;D;X;Q;C;J#c;o;y#S/DoQSU,JSCD,oXUSJ,yJS;U/D,JQ,e,oJ,y;D/C,CJJ,J,JCCc,S,e;X/Cc,S,U,cXSXJ,yCoJy,yXQXD;Q/DC,XJX,o;C/CXQ,Q,oJcU;J/Q,yQ");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;U;D;X;Q;C;J#c;o;y#S/DoQS,DoQSU,JSC,JSCD,oQS,oQSU,oSJ,oUSJ,oXSJ,oXUSJ,yJS;U/CJJ,CQ,CXQ,DC,DoQS,DoQSU,JCCc,JQ,JSC,JSCD,JX,XJ,XJX,o,oJ,oJc,oJcU,oQS,oQSU,oSJ,oUSJ,oXSJ,oXUSJ,y,yJS,yQ;D/CJJ,CQ,CXQ,DC,DoQS,DoQSU,JCCc,JSC,JSCD,JX,XJ,XJX,o,oJc,oJcU,oQS,oQSU,oSJ,oUSJ,oXSJ,oXUSJ,yJS,yQ;X/CJJ,CQ,CXQ,Cc,DC,DoQS,DoQSU,JCCc,JQ,JSC,JSCD,JX,XJ,XJX,cSJ,cSXJ,cXSJ,cXSXJ,o,oJ,oJc,oJcU,oQS,oQSU,oSJ,oUSJ,oXSJ,oXUSJ,y,yCoJy,yJS,yQ,yQD,yQX,yQXD,yXQ,yXQD,yXQX,yXQXD;Q/CQ,CXQ,DC,JX,XJ,XJX,o,oJc,oJcU,yQ;C/CQ,CXQ,DC,JX,XJ,XJX,o,oJc,oJcU,yQ;J/CQ,CXQ,DC,JX,XJ,XJX,o,oJc,oJcU,yQ", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination3() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;Q;M;Y;D;R;U#a;l;p;w;y#S/lYa,pQp;Q/R,pQy,y;M/MU,QpMSa,RlRM,UDYyU;Y/UUUYy,e,lU;D/D,UwDpS,e,lYy;R/D,DQpSQ,S,SURlM,lYUwR;U/MSlD,Q,UQUl,wSy");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;Q;M;Y;D;R;U#a;l;p;w;y#S/lYa,la,pQp,pp;Q/DQpS,DQpSQ,DpS,DpSQ,QpS,QpSQ,SRlM,SURlM,SUlM,SlM,UwDpS,UwpS,lUw,lUwR,lYUw,lYUwR,lYa,lYw,lYwR,lYy,la,lw,lwR,ly,pQp,pQy,pS,pSQ,pp,py,wDpS,wpS,y;M/DYy,DYyU,Dy,DyU,MU,QpMSa,RlM,RlRM,UDYy,UDYyU,UDy,UDyU,UYy,UYyU,Uy,UyU,Yy,YyU,lM,lRM,pMSa,y,yU;Y/UUUYy,UUUy,UUYy,UUy,UYy,Uy,Yy,l,lU,y;D/UwDpS,UwpS,lYy,ly,wDpS,wpS;R/DQpS,DQpSQ,DpS,DpSQ,QpS,QpSQ,SRlM,SURlM,SUlM,SlM,UwDpS,UwpS,lUw,lUwR,lYUw,lYUwR,lYa,lYw,lYwR,lYy,la,lw,lwR,ly,pQp,pS,pSQ,pp,wDpS,wpS;U/DQpS,DQpSQ,DpS,DpSQ,MSl,MSlD,QUl,Ql,QpS,QpSQ,SRlM,SURlM,SUlM,SlM,UQUl,UQl,UUl,Ul,UwDpS,UwpS,l,lUw,lUwR,lYUw,lYUwR,lYa,lYw,lYwR,lYy,la,lw,lwR,ly,pQp,pQy,pS,pSQ,pp,py,wDpS,wSy,wpS,y", cfgEpsUnitElim.toString());
	}

	@Test
	public void testCfgEpsilonUnitRuleElimination4() {
		CfgEpsUnitElim cfgEpsUnitElim= new CfgEpsUnitElim("S;X;C;W;U;I;O#a;d;h;j#S/IaI,Id,jI;X/COj,OOd,Sh,SjCa,e;C/S,U,dCU,e;W/Cd,OOhSh,WS,XaXXU;U/C,I,IX,UWWWC;I/CWISX,I,UOXdX,X,e,h;O/OW,UC,UdO,jWWXO");
		cfgEpsUnitElim.eliminateEpsilonRules();
		cfgEpsUnitElim.eliminateUnitRules();
		assertEquals("S;X;C;W;U;I;O#a;d;h;j#S/Ia,IaI,Id,a,aI,d,j,jI;X/COj,Cj,OOd,Od,Oj,Sh,SjCa,Sja,d,j;C/COj,CWIS,CWISX,CWS,CWSX,Cj,IX,Ia,IaI,Id,OOd,OXd,OXdX,Od,OdX,Oj,Sh,SjCa,Sja,UOXd,UOXdX,UOd,UOdX,UWWW,UWWWC,UXd,UXdX,Ud,UdX,WIS,WISX,WS,WSX,WWW,WWWC,Xd,XdX,a,aI,d,dC,dCU,dU,dX,h,j,jI;W/Cd,OOhSh,OhSh,WS,Xa,XaU,XaX,XaXU,XaXX,XaXXU,a,aU,aX,aXU,aXX,aXXU,d,hSh;U/COj,CWIS,CWISX,CWS,CWSX,Cj,IX,Ia,IaI,Id,OOd,OXd,OXdX,Od,OdX,Oj,Sh,SjCa,Sja,UOXd,UOXdX,UOd,UOdX,UWWW,UWWWC,UXd,UXdX,Ud,UdX,WIS,WISX,WS,WSX,WWW,WWWC,Xd,XdX,a,aI,d,dC,dCU,dU,dX,h,j,jI;I/COj,CWIS,CWISX,CWS,CWSX,Cj,OOd,OXd,OXdX,Od,OdX,Oj,Sh,SjCa,Sja,UOXd,UOXdX,UOd,UOdX,UXd,UXdX,Ud,UdX,WIS,WISX,WS,WSX,Xd,XdX,d,dX,h,j;O/COj,CWIS,CWISX,CWS,CWSX,Cd,Cj,IX,Ia,IaI,Id,OOd,OOhSh,OW,OXd,OXdX,Od,OdX,OhSh,Oj,Sh,SjCa,Sja,UC,UOXd,UOXdX,UOd,UOdX,UWWW,UWWWC,UXd,UXdX,Ud,UdO,UdX,WIS,WISX,WS,WSX,WWW,WWWC,Xa,XaU,XaX,XaXU,XaXX,XaXXU,Xd,XdX,a,aI,aU,aX,aXU,aXX,aXXU,d,dC,dCU,dO,dU,dX,h,hSh,j,jI,jWW,jWWO,jWWX,jWWXO", cfgEpsUnitElim.toString());
	}

}