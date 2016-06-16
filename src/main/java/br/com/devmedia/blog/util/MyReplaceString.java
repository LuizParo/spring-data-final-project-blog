package br.com.devmedia.blog.util;

import java.io.Serializable;
import java.text.Normalizer;

import org.springframework.stereotype.Service;

@Service
public class MyReplaceString implements Serializable {
    private static final long serialVersionUID = 1L;

    public String formatPermalink(String value) {
        return Normalizer.normalize(value.toLowerCase().trim(), Normalizer.Form.NFD)
                .replaceAll("\\s", "_")
                .replaceAll("\\_+", "_")
                .replaceAll("\\W", "");
    }
}