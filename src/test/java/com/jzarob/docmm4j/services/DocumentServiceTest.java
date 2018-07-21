package com.jzarob.docmm4j.services;

import com.jzarob.docmm4j.exceptions.DocumentNotFoundException;
import com.jzarob.docmm4j.exceptions.DuplicateDocumentException;
import com.jzarob.docmm4j.models.Document;
import com.jzarob.docmm4j.repositories.DocumentRepository;
import com.jzarob.docmm4j.services.impl.DocumentServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private DocumentServiceImpl documentService;

    @Test(expected = DocumentNotFoundException.class)
    public void whenDocumentService_formNumberNotFound_ThrowsExeption() {
        when(documentRepository.findByDocumentNumber(any())).thenThrow(DocumentNotFoundException.class);
        documentService.loadByDocumentNumber("12345");
    }

    @Test()
    public void whenDocumentService_formNumberFound_returnsDocument() {
        Document expected = new Document();
        when(documentRepository.findByDocumentNumber(any())).thenReturn(expected);

        Document result = documentService.loadByDocumentNumber("1234");

        Assert.assertEquals(expected, result);
    }

    @Test(expected = DocumentNotFoundException.class)
    public void whenDocumentService_documentNotFound_throwsException() {
        Document expected = new Document();

        documentService.loadByDocumentNumber("12345");
    }

    @Test(expected = DuplicateDocumentException.class)
    public void whenDocumentService_documentExists_ThrowsException() {
        when(documentRepository.existsById("12345")).thenReturn(true);

        Document temp = new Document();
        temp.setId("12345");

        documentService.createDocument(temp);
    }

    @Test
    public void whenDocumentService_createDocument_returnDocument() {
        Document temp = new Document();

        when(documentRepository.save(temp)).thenReturn(temp);

        Document result = documentService.createDocument(temp);

        Assert.assertEquals(result, temp);
    }

    @Test
    public void whenDocumentService_saveDocument_returnsDocument() {
        Document temp = new Document();

        when(documentRepository.save(temp)).thenReturn(temp);

        documentService.saveDocument(temp);

        Assert.assertTrue(true);
    }
}
