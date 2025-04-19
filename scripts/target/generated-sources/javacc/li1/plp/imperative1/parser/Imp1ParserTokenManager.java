/* Generated By:JavaCC: Do not edit this line. Imp1ParserTokenManager.java */
package li1.plp.imperative1.parser;
import li1.plp.expressions1.util.*;
import li1.plp.expressions2.expression.*;
import li1.plp.imperative1.command.*;
import li1.plp.imperative1.declaration.*;
import li1.plp.imperative1.memory.*;
import li1.plp.imperative1.*;
import java.util.*;

/** Token Manager. */
public class Imp1ParserTokenManager implements Imp1ParserConstants
{

  /** Debug output. */
  public static  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public static  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private static final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x200000000000000L) != 0L)
            return 13;
         if ((active0 & 0x7e00fffe00L) != 0L)
         {
            jjmatchedKind = 67;
            return 7;
         }
         return -1;
      case 1:
         if ((active0 & 0x3e00dbfa00L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 1;
            return 7;
         }
         if ((active0 & 0x4000240400L) != 0L)
            return 7;
         return -1;
      case 2:
         if ((active0 & 0x3e00db7000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 2;
            return 7;
         }
         if ((active0 & 0x8a00L) != 0L)
            return 7;
         return -1;
      case 3:
         if ((active0 & 0x1200c92000L) != 0L)
            return 7;
         if ((active0 & 0x2c00125000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 3;
            return 7;
         }
         return -1;
      case 4:
         if ((active0 & 0x400124000L) != 0L)
            return 7;
         if ((active0 & 0x2800001000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 4;
            return 7;
         }
         return -1;
      case 5:
         if ((active0 & 0x2800000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 5;
            return 7;
         }
         if ((active0 & 0x1000L) != 0L)
            return 7;
         return -1;
      case 6:
         if ((active0 & 0x2800000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 6;
            return 7;
         }
         return -1;
      case 7:
         if ((active0 & 0x2800000000L) != 0L)
         {
            jjmatchedKind = 67;
            jjmatchedPos = 7;
            return 7;
         }
         return -1;
      default :
         return -1;
   }
}
private static final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
static private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
static private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         jjmatchedKind = 43;
         return jjMoveStringLiteralDfa1_0(0x4000000000000L);
      case 37:
         return jjStopAtPos(0, 61);
      case 38:
         jjmatchedKind = 58;
         return jjMoveStringLiteralDfa1_0(0x10000000000000L);
      case 40:
         return jjStopAtPos(0, 24);
      case 41:
         return jjStopAtPos(0, 25);
      case 42:
         return jjStopAtPos(0, 56);
      case 43:
         jjmatchedKind = 54;
         return jjMoveStringLiteralDfa1_0(0x20000000000000L);
      case 44:
         return jjStopAtPos(0, 31);
      case 45:
         return jjStopAtPos(0, 55);
      case 46:
         return jjStopAtPos(0, 32);
      case 47:
         return jjStartNfaWithStates_0(0, 57, 13);
      case 58:
         jjmatchedKind = 46;
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      case 59:
         return jjStopAtPos(0, 30);
      case 60:
         jjmatchedKind = 42;
         return jjMoveStringLiteralDfa1_0(0x1000000000000L);
      case 61:
         jjmatchedKind = 40;
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 62:
         jjmatchedKind = 41;
         return jjMoveStringLiteralDfa1_0(0x2000000000000L);
      case 63:
         return jjStopAtPos(0, 45);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x4000000000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x400000000L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x200000000L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x1000000000L);
      case 84:
         return jjMoveStringLiteralDfa1_0(0x2000000000L);
      case 91:
         return jjStopAtPos(0, 28);
      case 93:
         return jjStopAtPos(0, 29);
      case 94:
         return jjStopAtPos(0, 60);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x40000L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x800000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x200000L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x1000L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x400L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x80000L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x402000L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0x8000L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x120000L);
      case 123:
         return jjStopAtPos(0, 26);
      case 124:
         jjmatchedKind = 59;
         return jjMoveStringLiteralDfa1_0(0x8000000000000L);
      case 125:
         return jjStopAtPos(0, 27);
      case 126:
         return jjStopAtPos(0, 44);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStopAtPos(1, 52);
         break;
      case 43:
         if ((active0 & 0x20000000000000L) != 0L)
            return jjStopAtPos(1, 53);
         break;
      case 61:
         if ((active0 & 0x8000000000L) != 0L)
            return jjStopAtPos(1, 39);
         else if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         else if ((active0 & 0x1000000000000L) != 0L)
            return jjStopAtPos(1, 48);
         else if ((active0 & 0x2000000000000L) != 0L)
            return jjStopAtPos(1, 49);
         else if ((active0 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 50);
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000000000L);
      case 76:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000000L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0xa00000000L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000000000L);
      case 83:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(1, 38, 7);
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0xc000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x81000L);
      case 102:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(1, 21, 7);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x420000L);
      case 107:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x800000L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x200L);
      case 111:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(1, 18, 7);
         return jjMoveStringLiteralDfa2_0(active0, 0x800L);
      case 114:
         if ((active0 & 0x400L) != 0L)
            return jjStartNfaWithStates_0(1, 10, 7);
         return jjMoveStringLiteralDfa2_0(active0, 0x102000L);
      case 124:
         if ((active0 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 51);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
static private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa3_0(active0, 0x2200000000L);
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000L);
      case 82:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000L);
      case 86:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 100:
         if ((active0 & 0x200L) != 0L)
            return jjStartNfaWithStates_0(2, 9, 7);
         break;
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x130000L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000L);
      case 114:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(2, 15, 7);
         break;
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L);
      case 116:
         if ((active0 & 0x800L) != 0L)
            return jjStartNfaWithStates_0(2, 11, 7);
         break;
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x2000L);
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
static private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000L);
      case 68:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(3, 33, 7);
         break;
      case 69:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 36, 7);
         break;
      case 77:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000L);
      case 78:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000L);
      case 100:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 7);
         break;
      case 101:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(3, 13, 7);
         else if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(3, 23, 7);
         break;
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x1000L);
      case 108:
         return jjMoveStringLiteralDfa4_0(active0, 0x20000L);
      case 110:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(3, 22, 7);
         break;
      case 112:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(3, 16, 7);
         break;
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
static private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000L);
      case 78:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(4, 34, 7);
         break;
      case 83:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000000L);
      case 101:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(4, 14, 7);
         else if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(4, 17, 7);
         else if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(4, 20, 7);
         break;
      case 116:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
static private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 70:
         return jjMoveStringLiteralDfa6_0(active0, 0x2000000000L);
      case 76:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000L);
      case 104:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(5, 12, 7);
         break;
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
static private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000L);
      case 79:
         return jjMoveStringLiteralDfa7_0(active0, 0x2000000000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
static private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 82:
         return jjMoveStringLiteralDfa8_0(active0, 0x2000000000L);
      case 90:
         return jjMoveStringLiteralDfa8_0(active0, 0x800000000L);
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
static private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 69:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(8, 35, 7);
         break;
      case 77:
         if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 37, 7);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
static private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 31;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 13:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(26, 27);
                  else if (curChar == 47)
                     jjCheckNAddStates(0, 2);
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 0:
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 62)
                        kind = 62;
                     jjCheckNAddTwoStates(1, 2);
                  }
                  else if (curChar == 47)
                     jjAddStates(3, 5);
                  else if (curChar == 48)
                  {
                     if (kind > 62)
                        kind = 62;
                     jjCheckNAddStates(6, 8);
                  }
                  else if (curChar == 34)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 62)
                     kind = 62;
                  jjCheckNAddTwoStates(1, 2);
                  break;
               case 3:
                  if (curChar == 34)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 4:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 5:
                  if (curChar == 34 && kind > 66)
                     kind = 66;
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjstateSet[jjnewStateCnt++] = 7;
                  break;
               case 8:
                  if (curChar != 48)
                     break;
                  if (kind > 62)
                     kind = 62;
                  jjCheckNAddStates(6, 8);
                  break;
               case 10:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 62)
                     kind = 62;
                  jjCheckNAddTwoStates(10, 2);
                  break;
               case 11:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 62)
                     kind = 62;
                  jjCheckNAddTwoStates(11, 2);
                  break;
               case 12:
                  if (curChar == 47)
                     jjAddStates(3, 5);
                  break;
               case 14:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(0, 2);
                  break;
               case 15:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 16:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 17:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 18:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 19:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 20:
                  if (curChar == 42)
                     jjCheckNAddStates(9, 11);
                  break;
               case 21:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(22, 20);
                  break;
               case 22:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(22, 20);
                  break;
               case 23:
                  if (curChar == 47 && kind > 7)
                     kind = 7;
                  break;
               case 24:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 25:
                  if (curChar == 42)
                     jjCheckNAddTwoStates(26, 27);
                  break;
               case 26:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(26, 27);
                  break;
               case 27:
                  if (curChar == 42)
                     jjCheckNAddStates(12, 14);
                  break;
               case 28:
                  if ((0xffff7bffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(29, 27);
                  break;
               case 29:
                  if ((0xfffffbffffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(29, 27);
                  break;
               case 30:
                  if (curChar == 47 && kind > 8)
                     kind = 8;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(7);
                  break;
               case 2:
                  if ((0x100000001000L & l) != 0L && kind > 62)
                     kind = 62;
                  break;
               case 4:
                  jjAddStates(15, 16);
                  break;
               case 7:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 67)
                     kind = 67;
                  jjCheckNAdd(7);
                  break;
               case 9:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(10);
                  break;
               case 10:
                  if ((0x7e0000007eL & l) == 0L)
                     break;
                  if (kind > 62)
                     kind = 62;
                  jjCheckNAddTwoStates(10, 2);
                  break;
               case 14:
                  jjAddStates(0, 2);
                  break;
               case 19:
                  jjCheckNAddTwoStates(19, 20);
                  break;
               case 21:
               case 22:
                  jjCheckNAddTwoStates(22, 20);
                  break;
               case 26:
                  jjCheckNAddTwoStates(26, 27);
                  break;
               case 28:
               case 29:
                  jjCheckNAddTwoStates(29, 27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 4:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(15, 16);
                  break;
               case 14:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(0, 2);
                  break;
               case 19:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(19, 20);
                  break;
               case 21:
               case 22:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(22, 20);
                  break;
               case 26:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(26, 27);
                  break;
               case 28:
               case 29:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(29, 27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 31 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   14, 15, 17, 13, 24, 25, 9, 11, 2, 20, 21, 23, 27, 28, 30, 4, 
   5, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, "\141\156\144", 
"\157\162", "\156\157\164", "\154\145\156\147\164\150", "\164\162\165\145", 
"\146\141\154\163\145", "\166\141\162", "\163\153\151\160", "\167\150\151\154\145", "\144\157", 
"\162\145\141\144", "\167\162\151\164\145", "\151\146", "\164\150\145\156", "\145\154\163\145", 
"\50", "\51", "\173", "\175", "\133", "\135", "\73", "\54", "\56", 
"\114\117\101\104", "\103\114\105\101\116", "\116\117\122\115\101\114\111\132\105", 
"\123\101\126\105", "\124\122\101\116\123\106\117\122\115", "\101\123", "\72\75", "\75", "\76", 
"\74", "\41", "\176", "\77", "\72", "\75\75", "\74\75", "\76\75", "\41\75", 
"\174\174", "\46\46", "\53\53", "\53", "\55", "\52", "\57", "\46", "\174", "\136", "\45", 
null, null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7ffffffffffffe01L, 0xcL, 
};
static final long[] jjtoSkip = {
   0x1feL, 0x0L, 
};
static final long[] jjtoSpecial = {
   0x1c0L, 0x0L, 
};
static protected JavaCharStream input_stream;
static private final int[] jjrounds = new int[31];
static private final int[] jjstateSet = new int[62];
static protected char curChar;
/** Constructor. */
public Imp1ParserTokenManager(JavaCharStream stream){
   if (input_stream != null)
      throw new TokenMgrError("ERROR: Second call to constructor of static lexer. You must use ReInit() to initialize the static variables.", TokenMgrError.STATIC_LEXER_ERROR);
   input_stream = stream;
}

/** Constructor. */
public Imp1ParserTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
static public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
static private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 31; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
static public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
static public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

static protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

static int curLexState = 0;
static int defaultLexState = 0;
static int jjnewStateCnt;
static int jjround;
static int jjmatchedPos;
static int jjmatchedKind;

/** Get the next Token. */
public static Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         matchedToken.specialToken = specialToken;
         return matchedToken;
      }
      else
      {
         if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
         {
            matchedToken = jjFillToken();
            if (specialToken == null)
               specialToken = matchedToken;
            else
            {
               matchedToken.specialToken = specialToken;
               specialToken = (specialToken.next = matchedToken);
            }
         }
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

static private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
static private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
static private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

static private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
