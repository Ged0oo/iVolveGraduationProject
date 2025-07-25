provider "aws" {
  region = var.aws_region
}

module "network" {
  source             = "./modules/network"
  vpc_cidr           = var.vpc_cidr
  public_subnet_cidr = var.public_subnet_cidr
  
}

module "server" {
  source        = "./modules/server"
  subnet_id     = module.network.public_subnet_id
  vpc_id        = module.network.vpc_id
  ami_id        = var.ami_id
  instance_type = var.instance_type
  key_name      = var.key_name
}
