package com.namedlock.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Feature;
import com.googlecode.aviator.Options;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAviator {

    @BeforeClass
    static public void before(){
        /**
         * 缓存表达式，避免OOM
         */
        AviatorEvaluator.getInstance().useLRUExpressionCache(100);
        /**
         * 执行速度优先
         */
        AviatorEvaluator.setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.EVAL);
        /**
         * 找不到属性时不要抛出异常
         */
        AviatorEvaluator.setOption(Options.NIL_WHEN_PROPERTY_NOT_FOUND, true);
        /**
         * 开启语法糖
         */
        AviatorEvaluator.setOption(Options.ENABLE_PROPERTY_SYNTAX_SUGAR, true);
        /**
         * 不希望env被aviator修改
         */
        AviatorEvaluator.setOption(Options.USE_USER_ENV_AS_TOP_ENV_DIRECTLY, false);
        /**
         * 设置最大循环次数上限，避免死循环出现
         */
        AviatorEvaluator.setOption(Options.MAX_LOOP_COUNT, 100);
        /**
         * 不能创建新对象
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.NewInstance);
        /**
         * 不能加载外部模块
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.Module);
        AviatorEvaluator.getInstance().disableFeature(Feature.ForLoop);
        AviatorEvaluator.getInstance().disableFeature(Feature.Lambda);
        /**
         * 关闭赋值
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.Assignment);
        AviatorEvaluator.getInstance().disableFeature(Feature.WhileLoop);
        AviatorEvaluator.getInstance().disableFeature(Feature.Return);
        /**
         * 不允许try-catch-throw Exception
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.ExceptionHandle);
        /**
         * 不支持定义变量
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.Let);
        /**
         * 关闭作用域
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.LexicalScope);
        /**
         * 关闭函数定义
         */
        AviatorEvaluator.getInstance().disableFeature(Feature.Fn);

    }

    @Test
    public void test_simple() {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
    }

    @Test
    public void testParams(){
        DomainObj domainObj = new DomainObj();
        domainObj.setAge(18);
        domainObj.setHeight(163.3f);
        domainObj.setName("bigJoe");
        domainObj.getAddress().setCity("hz");


        Object [] params = new Object[]{domainObj};
        String expression = "string.startsWith($1.name,'big') && $1.address.city == 'hz'";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        Boolean result =
                (Boolean) compiledExp.execute(compiledExp.newEnv("$1", domainObj));
        Assert.assertTrue(result);

        String bigcomputer="";
    }


}
