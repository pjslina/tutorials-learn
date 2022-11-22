package com.panjin.streamutils;

import org.junit.Test;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.panjin.streamutils.CopyStream.getStringFromInputStream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CopyStreamIntegrationTest {

    @Test
    public void whenCopyInputStreamToOutputStream_thenCorrect() throws IOException {
        String inputFileName = "src/test/resources/input.txt";
        String outputFileName = "src/test/resources/output.txt";
        var file = new File(outputFileName);
        var in = new FileInputStream(inputFileName);
        var out = new FileOutputStream(file);

        StreamUtils.copy(in, out);
        assertTrue(file.exists());

        String inputFileContent = getStringFromInputStream(new FileInputStream(inputFileName));
        String outputFileContent = getStringFromInputStream(new FileInputStream(outputFileName));
        assertEquals(inputFileContent, outputFileContent);
    }
}
