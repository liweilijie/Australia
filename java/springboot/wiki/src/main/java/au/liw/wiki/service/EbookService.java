package au.liw.wiki.service;

import au.liw.wiki.domain.Ebook;
import au.liw.wiki.mapper.EbookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
