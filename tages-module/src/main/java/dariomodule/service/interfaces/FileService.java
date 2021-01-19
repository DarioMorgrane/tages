package dariomodule.service.interfaces;

import dariomodule.model.File;
import dariomodule.web.dto.FileJson;

public interface FileService {

    void save(File file);

    File findFileById(Long id);

    File convertToModel(Long id, FileJson fileJson);
}
