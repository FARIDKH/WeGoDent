package com.company.WeGoDent.services;

import com.company.WeGoDent.entity.Supplier;
import com.company.WeGoDent.exceptions.DuplicateException.ResourceNotFoundException;
import com.company.WeGoDent.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Long id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id : " + id));
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id  : " + id));

        supplier.setName(supplierDetails.getName());
        supplier.setContactInfo(supplierDetails.getContactInfo());

        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id  : " + id));
        supplierRepository.delete(supplier);
    }
}
