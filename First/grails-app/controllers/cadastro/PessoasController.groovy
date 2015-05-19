package cadastro

import org.springframework.dao.DataIntegrityViolationException

class PessoasController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pessoasInstanceList: Pessoas.list(params), pessoasInstanceTotal: Pessoas.count()]
    }

    def create() {
        [pessoasInstance: new Pessoas(params)]
    }

    def save() {
        def pessoasInstance = new Pessoas(params)
        if (!pessoasInstance.save(flush: true)) {
            render(view: "create", model: [pessoasInstance: pessoasInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), pessoasInstance.id])
        redirect(action: "show", id: pessoasInstance.id)
    }

    def show(Long id) {
        def pessoasInstance = Pessoas.get(id)
        if (!pessoasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "list")
            return
        }

        [pessoasInstance: pessoasInstance]
    }

    def edit(Long id) {
        def pessoasInstance = Pessoas.get(id)
        if (!pessoasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "list")
            return
        }

        [pessoasInstance: pessoasInstance]
    }

    def update(Long id, Long version) {
        def pessoasInstance = Pessoas.get(id)
        if (!pessoasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pessoasInstance.version > version) {
                pessoasInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pessoas.label', default: 'Pessoas')] as Object[],
                          "Another user has updated this Pessoas while you were editing")
                render(view: "edit", model: [pessoasInstance: pessoasInstance])
                return
            }
        }

        pessoasInstance.properties = params

        if (!pessoasInstance.save(flush: true)) {
            render(view: "edit", model: [pessoasInstance: pessoasInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), pessoasInstance.id])
        redirect(action: "show", id: pessoasInstance.id)
    }

    def delete(Long id) {
        def pessoasInstance = Pessoas.get(id)
        if (!pessoasInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "list")
            return
        }

        try {
            pessoasInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pessoas.label', default: 'Pessoas'), id])
            redirect(action: "show", id: id)
        }
    }
}
