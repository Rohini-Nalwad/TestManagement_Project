package com.service.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Category;
import com.entity.Question;
import com.exception.QuestionNotFoundException;
import com.repository.CategoryRepository;
import com.repository.QuestionRepository;
import com.service.QuestionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	

    @Override
    public List<Question> getAllQuestions() {
        log.info("Fetching all questions");
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        log.info("Fetching question by id: {}", id);
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question not found with id: " + id, null));
    }

    @Override
    public Question saveQuestion(Question question) {
        log.info("Saving question: {}", question);
        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        log.info("Deleting question with id: {}", id);
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException("Question not found with id: " + id, null);
        }
        questionRepository.deleteById(id);
    }
    
    @Override
    public void importQuestionsFromExcel(List<MultipartFile> multipartFiles) throws IOException {
        for (MultipartFile file : multipartFiles) {
            try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }

                    String content = getStringValue(row.getCell(0));
                    String option1 = getStringValue(row.getCell(1));
                    String option2 = getStringValue(row.getCell(2));
                    String option3 = getStringValue(row.getCell(3));
                    String option4 = getStringValue(row.getCell(4));
                    String answer = getStringValue(row.getCell(5));
                    String mark = getStringValue(row.getCell(6));
                    String title = getStringValue(row.getCell(7));

                    Category category = categoryRepository.findByTitle(title)
                          .orElseGet(() -> categoryRepository.save(new Category(title)));

                    Question question = new Question();
                    question.setContent(content);
                    question.setOption1(option1);
                    question.setOption2(option2);
                    question.setOption3(option3);
                    question.setOption4(option4);
                    question.setAnswer(answer);
                    question.setMarks(mark);
                    question.setCategory(category);
                    questionRepository.save(question);
                }
            }
        }
    }

    private String getStringValue(Cell cell) {
        return cell != null ? cell.toString() : null;
    }

}

//    @Override
//    public List<Question> importQuestionsFromExcel(InputStream excelInputStream) throws IOException {
//        Workbook workbook = WorkbookFactory.create(excelInputStream);
//        List<Question> importedQuestions = new ArrayList<>();
//
//        Sheet sheet = workbook.getSheetAt(0); 
//        for (Row row : sheet) {
//            Question question = new Question();
//            question.setContent(getCellValue(row.getCell(0)));
//            question.setOption1(getCellValue(row.getCell(1)));
//            question.setOption2(getCellValue(row.getCell(2)));
//            question.setOption3(getCellValue(row.getCell(3)));
//            question.setOption4(getCellValue(row.getCell(4)));
//            question.setAnswer(getCellValue(row.getCell(5)));
//            question.setMarks(getCellValue(row.getCell(6)));
//
//            importedQuestions.add(questionRepository.save(question));
//        }
//
//        return importedQuestions;
//    }
//
//    private String getCellValue(Cell cell) {
//        if (cell == null) {
//            return null;
//        }
//
//        cell.setCellType(CellType.STRING);
//        return cell.getStringCellValue();
//    }
//
//	@Override
//	public Optional<Category> findByTitle(String title) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//}