package com.profitbricks.sdk.model

import com.profitbricks.sdk.annotation.*
import groovy.transform.*

/**
 * a storage volume POGO
 * see: https://devops.profitbricks.com/api/cloud/v4/#volumes
 *
 * Created by fudge on 03/02/17.
 * Copyright (c) 2017, ProfitBricks GmbH
 */
@ToString(includeNames = true, ignoreNulls = true, includeSuperProperties = true, includePackage = false, excludes = ['resource', 'dataCenter'])
@EqualsAndHashCode(callSuper = true)
final class Volume extends ModelBase {
    DataCenter dataCenter

    @Creatable @SuppressWarnings("GroovyUnusedDeclaration")
    String type = 'HDD', availabilityZone = 'AUTO', image, licenceType = 'UNKNOWN'
    @Creatable @Updatable @SuppressWarnings("GroovyUnusedDeclaration")
    String name,  bus = 'VIRTIO'
    @Creatable @SuppressWarnings("GroovyUnusedDeclaration")
    String imageAlias
    @Creatable @SuppressWarnings("GroovyUnusedDeclaration")
    List<String> sshKeys
    @Creatable @SuppressWarnings("GroovyUnusedDeclaration")
    String imagePassword
    @Creatable @Updatable @SuppressWarnings("GroovyUnusedDeclaration")
    int size
    @Readable @SuppressWarnings("GroovyUnusedDeclaration")
    int deviceNumber

    @Override
    final Volume create() {
        //in case both image and licenceType is provided ignore licenceType and take image
        licenceType = (image != null || imageAlias != null) ? null : licenceType
        (super.create() as Volume)?.with dataCenter 
    }

    @Override
    final Volume read(final id = id) { (super.read(id) as Volume)?.with dataCenter }

    @Override
    final String getResource() { "${dataCenter.resource}/${dataCenter.id}/volumes" }

    private final Volume with(final DataCenter dc) { dataCenter = dc; this }
}
