package com.tuxt.mytest;

import com.tuxt.mytest.mapstruct.Student;
import com.tuxt.mytest.mapstruct.StudentDTO;
import com.tuxt.mytest.mapstruct.StudentMapper;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MapStructTest {

    @Test
    public void test(){
        StudentDTO studentDTO=new StudentDTO("Tom",18,1,"111");
        Student student = StudentMapper.MAPPER.dtoToBo(studentDTO);
        assertThat( student.getName() ).isEqualTo("Tom");

    }
}
