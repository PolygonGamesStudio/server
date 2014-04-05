package UtilsTest;

import base.GameMechanic;
import base.MessageSystem;
import gameMechanic.GameMechanicImpl;
import messageSystem.MessageSystemImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.ExpectedExceptions;
import org.testng.annotations.Test;
import utils.ReflectionHelper;

public class ReflectionHelperTest {

    private static String correctClassName = "resource.GameSettings";
    private static String incorrectClassName = "blah-blah";

    @Test
    public void createCorrectInstanceTest() {
        Assert.assertNotNull(ReflectionHelper.createInstance(correctClassName));
    }


    @Test
    public void createIncorrectInstanceTest() {
        Assert.assertNull(ReflectionHelper.createInstance(incorrectClassName));
    }

    private class Student {
        private String name;
        private int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    private Student bauStudent;
    private final String bauStudentName = "El Studento";
    private final int bauStudentAge = 42;

    @BeforeMethod
    public void setUp() throws Exception {
        bauStudent = new Student();
        bauStudent.setName(bauStudentName);
        bauStudent.setAge(bauStudentAge);
    }

    @Test
    public void setIntField() {
        String studentName = "Alexei";

        ReflectionHelper.setFieldValue(bauStudent, "name", studentName);
        Assert.assertEquals(bauStudent.getName(), studentName);
    }

    @Test
    public void setStringField() {
        int studentAge = 22;

        ReflectionHelper.setFieldValue(bauStudent, "age", String.valueOf(studentAge));
        Assert.assertEquals(bauStudent.getAge(), studentAge);
    }

    @Test
    public void setNotSupportedClassField() throws Exception {
        ReflectionHelper.setFieldValue(bauStudent, "age", "3.1415");
        Assert.assertEquals(bauStudent.getAge(), bauStudentAge);
        Assert.assertEquals(bauStudent.getName(), bauStudentName);
    }

    @Test
    public void setStringInsteadOfInt() throws Exception {
        ReflectionHelper.setFieldValue(bauStudent, "age", "wake up");
        Assert.assertEquals(bauStudent.getAge(), bauStudentAge);
        Assert.assertEquals(bauStudent.getName(), bauStudentName);
    }

    @Test
    public void setWrongField() {
        ReflectionHelper.setFieldValue(bauStudent, "some field", "3.1415");
        Assert.assertEquals(bauStudent.getAge(), bauStudentAge);
        Assert.assertEquals(bauStudent.getName(), bauStudentName);
    }
}
