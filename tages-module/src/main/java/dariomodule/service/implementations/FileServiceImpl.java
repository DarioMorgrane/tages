package dariomodule.service.implementations;

import dariomodule.model.File;
import dariomodule.model.Order;
import dariomodule.repository.FileRepository;
import dariomodule.service.interfaces.FileService;
import dariomodule.service.interfaces.OrderService;
import dariomodule.web.dto.FileJson;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class FileServiceImpl implements FileService {

    private final OrderService orderService;
    private final FileRepository repository;
    @Value("${file-download-template}")
    private String fileDownloadTemplate;

    public FileServiceImpl(OrderService orderService, FileRepository repository) {
        this.orderService = orderService;
        this.repository = repository;
    }

    @Override
    public void save(File file) {
        orderService.saveOrder(file.getOrder());
        repository.save(file);
    }

    @Override
    public File findFileById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No files with provided id exist!"));
    }

    @Override
    public File convertToModel(Long id, FileJson fileJson) {
        File file = new File();
        file.setId(id);
        file.setName(fileJson.getName());
        file.setContent(Base64.decodeBase64(fileJson.getContent().getBytes(StandardCharsets.UTF_8)));
        Order order = orderService.findOrderById(id);
        if (order.getFileDownloadPath() != null) {
            throw new RuntimeException("To order with provided id file already added!");
        }
        order.setFileDownloadPath(fileDownloadTemplate + file.getId());
        file.setOrder(order);
        return file;
    }
}
