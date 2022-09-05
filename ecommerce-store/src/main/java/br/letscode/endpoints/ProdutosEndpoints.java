package br.letscode.endpoints;

import br.letscode.models.Produto;
import br.letscode.services.impl.ProdutosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class ProdutosEndpoints {

    @Autowired
    ProdutosServiceImpl produtosService;

    @RequestMapping(path = "/produtos/categorias", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllCategories() {

        // TODO outras acoes da API

        return ResponseEntity.ok(produtosService.listarCategorias());
    }

    // @RequestMapping(path="/produtos/", method = RequestMethod.DELETE, produces =
    // APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> deletarComID(@PathVariable Long id) {
    // //TODO outras acoes da API
    // produtosService.deleteById(id);
    // return ResponseEntity.ok("deletado com sucesso");
    // }

    // }

    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletarComID(@PathVariable Long id) {
        try {
            produtosService.deletarProduto(id);
            return new ResponseEntity<String>("Produto deletado com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/produtos/", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        return ResponseEntity.ok(produtosService.novoProduto(produto));
    }

    @RequestMapping(path = "/produtos/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> recuperarProdutoComID(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.buscarProduto(id));
    }

    @RequestMapping(path = "/produtos/", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        return new ResponseEntity<List<Produto>>(produtosService.listarTodosProdutos(), HttpStatus.OK);
    }
}
