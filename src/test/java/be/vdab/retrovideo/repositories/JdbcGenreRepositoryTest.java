package be.vdab.retrovideo.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcGenreRespository.class)
public class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GENRES = "genres";
    @Autowired
    private JdbcGenreRespository repository;

    @Test public void findAll(){
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(GENRES))
        .extracting(genre -> genre.getId()).isSorted();
    }

    @Test public void findById(){
        assertThat(repository.findById(1).get().getNaam()).isEqualTo("Aktiefilm");
    }
}
