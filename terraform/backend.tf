terraform {
  backend "s3" {
    bucket = "ivolve-bucket-mnagy156"
    key    = "ivolve/terraform.tfstate"
    region = "us-west-1"
    encrypt = true
  }
}
