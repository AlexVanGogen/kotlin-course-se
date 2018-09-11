package ru.hse.spb

import org.junit.Assert.assertEquals
import org.junit.Test

class TestSource {

    @Test
    fun testSolutionOnPublicExample1() {
        assertEquals(
                listOf(2, 1, 0, 1, 0),
                solve("""
                    4
                    void f(int,T)
                    void  f(T, T)
                     void foo123   ( int,  double,  string,string  )
                      void  p(T,double)
                    3
                    int a
                     string    s
                    double x123
                    5
                    f(a,  a)
                      f(s,a   )
                    foo   (a,s,s)
                     f  (  s  ,x123)
                    proc(a)
                    """.trimIndent())
        )
    }

    @Test
    fun testSolutionOnPublicExample2() {
        assertEquals(
                listOf(1, 3, 0, 0, 2),
                solve("""
                    6
                    void f(string,double,int)
                    void f(int)
                       void f  ( T  )
                    void procedure(int,double)
                    void f  (T, double,int)
                    void f(string, T,T)
                    4
                     int a
                     int x
                    string  t
                    double  val
                    5
                    f(t, a, a)
                    f(t,val,a)
                    f(val,a, val)
                     solve300(val, val)
                    f  (x)
                    """.trimIndent())
        )
    }
}