package au.liw.wiki.service;

import au.liw.wiki.domain.Ebook;
import au.liw.wiki.domain.EbookExample;
import au.liw.wiki.mapper.EbookMapper;
import au.liw.wiki.req.EbookReq;
import au.liw.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Autowired
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebooks) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
//            ebookResp.setId(123L);
            respList.add(ebookResp);
        }
        return respList;
    }
}
