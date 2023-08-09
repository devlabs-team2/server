package com.dlteam2.server.Common;

import com.dlteam2.server.Common.Builder.BuilderSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest()
@ActiveProfiles("test")
public abstract class ServiceTest {
}
