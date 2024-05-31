package au.liw.wiki.service;

import au.liw.wiki.domain.Ebook;
import au.liw.wiki.domain.EbookExample;
import au.liw.wiki.mapper.EbookMapper;
import au.liw.wiki.req.EbookReq;
import au.liw.wiki.resp.EbookResp;
import au.liw.wiki.util.CopyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


        List<EbookResp> respList = CopyUtil.copyList(ebooks, EbookResp.class);

        return respList;
    }
}
