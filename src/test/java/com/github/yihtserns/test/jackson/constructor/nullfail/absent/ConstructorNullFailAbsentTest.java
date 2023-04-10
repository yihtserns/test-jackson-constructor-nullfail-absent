/*
 * Copyright 2023 yihtserns.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yihtserns.test.jackson.constructor.nullfail.absent;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.Test;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ConstructorNullFailAbsentTest {

    private ObjectMapper mapper = new ObjectMapper().registerModule(new Jdk8Module());

    // === Just to show what value the POJO will have when deserialized with empty hash

    @Test
    public void demo_Setter_EmptyJsonHash_PrintDeser() throws Exception {
        System.out.println(mapper.readValue("{}", BeanWithSetter.class));
    }

    @Test
    public void demo_Constructor_EmptyJsonHash_PrintDeser() throws Exception {
        System.out.println(mapper.readValue("{}", BeanWithConstructor.class));
    }

    // === Fail explicit null + explicit `: null` in JSON

    @Test
    public void demo_Setter_ExplicitNull_ShouldThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        mapper.readValue("{\"intVal\": null}", BeanWithSetter.class);
    }

    @Test
    public void demo_Constructor_ExplicitNull_ShouldThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        mapper.readValue("{\"intVal\": null}", BeanWithConstructor.class);
    }

    // === Fail explicit null + all absent values (empty hash) in JSON

    @Test
    public void demo_Setter_AllAbsentValues_ShouldNotThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        System.out.println(mapper.readValue("{}", BeanWithSetter.class));
    }

    @Test
    public void demo_Constructor_AllAbsentValues_ShouldNotThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        System.out.println(mapper.readValue("{}", BeanWithConstructor.class));
    }

    // === Fail explicit null + some absent values in JSON

    @Test
    public void demo_Setter_SomeAbsentValues_ShouldNotThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        System.out.println(mapper.readValue("{\"stringVal\": \"A\"}", BeanWithSetter.class));
    }

    @Test
    public void demo_Constructor_SomeAbsentValues_ShouldNotThrow() throws Exception {
        mapper.setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.FAIL));

        System.out.println(mapper.readValue("{\"stringVal\": \"A\"}", BeanWithConstructor.class));
    }

    public static class BeanWithConstructor extends BaseBean {

        @ConstructorProperties({"stringVal", "intVal", "intWrapperVal", "intArrayVal", "boolVal", "boolWrapperVal", "bigDecimalVal", "doubleVal", "enumVal", "collVal", "listVal", "setVal", "optVal", "beanVal"})
        public BeanWithConstructor(String stringVal, int intVal, Integer intWrapperVal, int[] intArrayVal, boolean boolVal, Boolean boolWrapperVal, BigDecimal bigDecimalVal, double doubleVal, TimeUnit enumVal, Collection<String> collVal, List<String> listVal, Set<String> setVal, Optional<String> optVal, BaseBean beanVal) {
            this.stringVal = stringVal;
            this.intVal = intVal;
            this.intWrapperVal = intWrapperVal;
            this.intArrayVal = intArrayVal;
            this.boolVal = boolVal;
            this.boolWrapperVal = boolWrapperVal;
            this.bigDecimalVal = bigDecimalVal;
            this.doubleVal = doubleVal;
            this.enumVal = enumVal;
            this.collVal = collVal;
            this.listVal = listVal;
            this.setVal = setVal;
            this.optVal = optVal;
            this.beanVal = beanVal;
        }

    }

    public static class BeanWithSetter extends BaseBean {

        public void setStringVal(String stringVal) {
            this.stringVal = stringVal;
        }

        public void setIntVal(int intVal) {
            this.intVal = intVal;
        }

        public void setIntWrapperVal(Integer intWrapperVal) {
            this.intWrapperVal = intWrapperVal;
        }

        public void setIntArrayVal(int[] intArrayVal) {
            this.intArrayVal = intArrayVal;
        }

        public void setBoolVal(boolean boolVal) {
            this.boolVal = boolVal;
        }

        public void setBoolWrapperVal(Boolean boolWrapperVal) {
            this.boolWrapperVal = boolWrapperVal;
        }

        public void setBigDecimalVal(BigDecimal bigDecimalVal) {
            this.bigDecimalVal = bigDecimalVal;
        }

        public void setDoubleVal(double doubleVal) {
            this.doubleVal = doubleVal;
        }

        public void setEnumVal(TimeUnit enumVal) {
            this.enumVal = enumVal;
        }

        public void setCollVal(Collection<String> collVal) {
            this.collVal = collVal;
        }

        public void setListVal(List<String> listVal) {
            this.listVal = listVal;
        }

        public void setSetVal(Set<String> setVal) {
            this.setVal = setVal;
        }

        public void setOptVal(Optional<String> optVal) {
            this.optVal = optVal;
        }

        public void setBeanVal(BaseBean beanVal) {
            this.beanVal = beanVal;
        }
    }

    public static abstract class BaseBean {

        protected String stringVal;
        protected int intVal;
        protected Integer intWrapperVal;
        protected int[] intArrayVal;
        protected boolean boolVal;
        protected Boolean boolWrapperVal;
        protected BigDecimal bigDecimalVal;
        protected double doubleVal;
        protected TimeUnit enumVal;
        protected Collection<String> collVal;
        protected List<String> listVal;
        protected Set<String> setVal;
        protected Optional<String> optVal;
        protected BaseBean beanVal;

        @Override
        public String toString() {
            return getClass().getSimpleName() + "{" +
                    "stringVal='" + stringVal + '\'' +
                    ", intVal=" + intVal +
                    ", intWrapperVal=" + intWrapperVal +
                    ", intArrayVal=" + Arrays.toString(intArrayVal) +
                    ", boolVal=" + boolVal +
                    ", bigDecimalVal=" + bigDecimalVal +
                    ", doubleVal=" + doubleVal +
                    ", enumVal=" + enumVal +
                    ", collVal=" + collVal +
                    ", listVal=" + listVal +
                    ", setVal=" + setVal +
                    ", optVal=" + optVal +
                    ", beanVal=" + beanVal +
                    '}';
        }
    }
}
