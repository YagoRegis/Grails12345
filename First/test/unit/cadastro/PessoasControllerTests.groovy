package cadastro



import org.junit.*
import grails.test.mixin.*

@TestFor(PessoasController)
@Mock(Pessoas)
class PessoasControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pessoas/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pessoasInstanceList.size() == 0
        assert model.pessoasInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pessoasInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pessoasInstance != null
        assert view == '/pessoas/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pessoas/show/1'
        assert controller.flash.message != null
        assert Pessoas.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoas/list'

        populateValidParams(params)
        def pessoas = new Pessoas(params)

        assert pessoas.save() != null

        params.id = pessoas.id

        def model = controller.show()

        assert model.pessoasInstance == pessoas
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoas/list'

        populateValidParams(params)
        def pessoas = new Pessoas(params)

        assert pessoas.save() != null

        params.id = pessoas.id

        def model = controller.edit()

        assert model.pessoasInstance == pessoas
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pessoas/list'

        response.reset()

        populateValidParams(params)
        def pessoas = new Pessoas(params)

        assert pessoas.save() != null

        // test invalid parameters in update
        params.id = pessoas.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pessoas/edit"
        assert model.pessoasInstance != null

        pessoas.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pessoas/show/$pessoas.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pessoas.clearErrors()

        populateValidParams(params)
        params.id = pessoas.id
        params.version = -1
        controller.update()

        assert view == "/pessoas/edit"
        assert model.pessoasInstance != null
        assert model.pessoasInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pessoas/list'

        response.reset()

        populateValidParams(params)
        def pessoas = new Pessoas(params)

        assert pessoas.save() != null
        assert Pessoas.count() == 1

        params.id = pessoas.id

        controller.delete()

        assert Pessoas.count() == 0
        assert Pessoas.get(pessoas.id) == null
        assert response.redirectedUrl == '/pessoas/list'
    }
}
