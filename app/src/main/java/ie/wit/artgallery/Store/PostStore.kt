package ie.wit.artgallery.Store

import ie.wit.artgallery.models.ArtModel

interface PostStore{
    fun findAll() : List<ArtModel>
    fun findById(id: Long) : ArtModel?
    fun create(post: ArtModel)
}