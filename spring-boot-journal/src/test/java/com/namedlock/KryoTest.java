package com.namedlock;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.google.common.collect.Lists;
import org.assertj.core.util.Sets;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class KryoTest {
    private Kryo kryo;
    private Output output;
    private Input input;


    @Before
    public void init() throws Exception {
        kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
//        CompatibleFieldSerializer s = new CompatibleFieldSerializer(kryo, A.class);
//        s.setFieldsCanBeNull(true);
//        s.setAcceptsNull(true);
//        s.setFieldsAsAccessible(true);
//        kryo.register(A.class, s);

        input = new Input(new FileInputStream("/Users/jingjing.zhijj/work/tmp/file.dat"));
    }

    private boolean supportWrite = false;

    @Test
    public void test() throws FileNotFoundException {
        if (supportWrite) {
            output = new Output(new FileOutputStream("/Users/jingjing.zhijj/work/tmp/file.dat"));
            A a = new A();
            a.setA(1);
            a.setB(2l);
            a.setC(3.2d);
            a.setD("hello");
            a.setList(Lists.newArrayList("a", "b", "c"));

            B bobj = new B();
            bobj.setName("bobj");
            bobj.setSets(Sets.newLinkedHashSet("bb", "cc", "dd"));
            a.setbObject(bobj);
            kryo.writeClassAndObject(output, a);
            output.flush();
            output.close();
        }

        Object theObject = kryo.readClassAndObject(input);
        input.close();
        System.out.println(theObject);
    }

}
